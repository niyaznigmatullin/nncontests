/*
 * main.cpp
 *
 *  Created on: 27.02.2010
 *      Author: pasha
 */
template<class T> class Vector {
protected:
	int capacity;
	int size;
	T *items;
public:
	Vector<T> () {
		capacity = 16;
		size = 0;
		items = new T[16];
	}
	void resize(int c) {
		if (c == 0)
			c = 1;
		capacity = c;
		T *tmp = new T[c];
		for (int i = 0; i < size; i++)
			tmp[i] = items[i];
		delete[] items;
		items = tmp;
	}
	;
	void set(int i, T x) {
		if (i >= 0 && i < size)
			items[i] = x;
	}
	const T get(int i) {
		if (i >= 0 && i < size)
			return items[i];
		return T();
	}
	T& operator [](int i) {
		if (i >= 0 && i < size)
			return items[i];
		return items[0];
	}
	int get_capacity() {
		return capacity;
	}
	;
	int get_size() {
		return size;
	}
	;
	void add(T x) {
		if (size == capacity)
			resize(capacity * 2);
		size++;
		items[size - 1] = x;
	}
};
template<> void Vector<char>::resize(int c) {
	if (c == 0)
		c = 1;
	capacity = c;
	char *tmp = new char[c];
	for (int i = 0; i < size; i++)
		tmp[i] = items[i];
	delete items;
	items = tmp;
}
;
template<class C> class Stack: public Vector<C> {
public:
	Stack<C> () :
		Vector<C> () {
	}
	;

	void push(C a) {
		if (Vector<C>::get_size() == Vector<C>::get_capacity())
			Vector<C>::resize(Vector<C>::get_capacity() * 2);
		Vector<C>::size++;
		Vector<C>::items[Vector<C>::size - 1] = a;
	}
	C pop() {
		if (Vector<C>::size == Vector<C>::capacity / 4)
			Vector<C>::resize(Vector<C>::capacity / 2);
		Vector<C>::size--;
		return Vector<C>::items[Vector<C>::size];
	}
	void clear() {
		Vector<C>::size = 0;
		Vector<C>::resize(1);
	}
};
struct List {
	struct Node {
		Node* next;
		int item;
	};
	Node *head;
	Node *tail;
	List() {
		head = 0;
		tail = 0;
	}
	void push_front(int a) {
		Node *tmp = new Node();
		tmp->next = head;
		tmp->item = a;
		head = tmp;
		if (head->next == 0)
			tail = head;
	}
	void push_back(int a) {
		Node *tmp = new Node();
		tmp->next = 0;
		tmp->item = a;
		if (tail == 0)
			head = tmp;
		else
			tail->next = tmp;
		tail = tmp;
	}
	int pop_front() {
		if (head != 0) {
			int tmp = head->item;
			Node *tmpNode = head;
			head = head->next;
			delete tmpNode;
			if (head == 0)
				tail = 0;
			return tmp;
		}
		return 0;
	}
};
class Queue {
	int *begin;
	int *end;
	int *items;
	int capacity;
	int size() {
		return (end - begin) + 1;
	}
	void resize(int c) {
		if (c <= 0)
			c = 1;
		if (c >= size()) {
			int *tmp = new int[c];
			capacity = c;
			for (int i = 0; i < size(); i++)
				tmp[i] = *(begin + i);
			int n = size();
			begin = tmp;
			end = begin + (n - 1);
			delete items;
			items = tmp;
		}
	}
public:
	Queue() {
		items = new int[1];
		capacity = 1;
		begin = items;
		end = begin - 1;
	}
	void push(int a) {
		if ((end - items) + 1 >= capacity)
			resize(size() * 2);
		end++;
		*end = a;
	}
	int pop() {
		if (size() > 0) {
			if (size() <= capacity / 4)
				resize(size() * 2);
			int tmp = *begin;
			begin++;
			return tmp;
		}
		return 0;
	}
};
#include <stdio.h>
#include <iostream>
#include <fstream>
#include <string>
#include <streambuf>
#include <stdlib.h>
#include <algorithm>
using namespace std;
const int BUF_SIZE = 100;
char *sbin = new char[BUF_SIZE];
char *sbout = new char[BUF_SIZE];

Vector<string> program = Vector<string> ();
Vector<pair<string, int> > labels = Vector<pair<string, int> > ();
//int *registers = new int[26]{0};
int registers[26] = {0};
Queue queue = Queue();
string stmp;
char *ctmp = new char[20];
int getline(string U)
{
	int i;
	for (i=0;i<labels.get_size();i++)
		if (!labels.get(i).first.compare(U))
			break;
	return labels.get(i).second;
}
int main() {
	ifstream sin;
	ofstream sout;
	sin.open("quack.in", ios::in);
	sout.open("quack.out", ios::out);
	sin.rdbuf()->pubsetbuf(sbin, BUF_SIZE);
	sout.rdbuf()->pubsetbuf(sbout, BUF_SIZE);
	while (!sin.eof()) {
		sin >> stmp;
		remove(stmp.begin(), stmp.end(), ' ');
		if (stmp == "") continue;
		if (stmp[0] == ':')
			labels.add(pair<string, int> (stmp.substr(1), program.get_size()));
	//	else
			program.add(stmp);
	}
	/*int k;
	for (int i = 0; i < program.get_size(); i++) {
		switch (program.get(i)[0]) {
		case 'J': {
			for (k = 0; k < labels.get_size(); k++)
				if (program.get(i).substr(1) == labels.get(k).first)
					break;
			sprintf(ctmp, "%d", labels.get(k).second);
			stmp = "J";
			stmp += ctmp;
			program.set(i, stmp);
		}
			break;
		case 'Z': {
			for (k = 0; k < labels.get_size(); k++)
				if (program.get(i).substr(2) == labels.get(k).first)
					break;
			sprintf(ctmp, "%d", labels.get(k).second);
			stmp = program.get(i).substr(0, 2);
			stmp += ctmp;
			program.set(i, stmp);
		}
			break;
		case 'E': {
			for (k = 0; k < labels.get_size(); k++)
				if (program.get(i).substr(3) == labels.get(k).first)
					break;
			sprintf(ctmp, "%d", labels.get(k).second);
			stmp = program.get(i).substr(0, 3);
			stmp += ctmp;
			program.set(i, stmp);
		}
			break;
		case 'G': {
			for (k = 0; k < labels.get_size(); k++)
				if (program.get(i).substr(3) == labels.get(k).first)
					break;
			sprintf(ctmp, "%d", labels.get(k).second);
			stmp = program.get(i).substr(0, 3);
			stmp += ctmp;
			program.set(i, stmp);
		}
			break;
		}
	}*/
	int cmdnum = 0;
	int x, y;

	string curcmd;
	bool isrunning = true;
	while (isrunning && cmdnum < program.get_size()) {
		curcmd = program.get(cmdnum);
		switch (curcmd[0]) {
		case '+': {
			x = queue.pop();
			y = queue.pop();
			queue.push((x + y) & (65536 - 1));
			cmdnum++;
		}
			break;
		case '-': {
			x = queue.pop();
			y = queue.pop();
			queue.push((x - y) & (65536 - 1));
			cmdnum++;
		}
			break;
		case '*': {
			x = queue.pop();
			y = queue.pop();
			queue.push((int)(((long long)x * y) & (65536 - 1)));
			cmdnum++;
		}
			break;
		case '/': {
			x = queue.pop();
			y = queue.pop();
			if (y != 0)
				queue.push(x / y);
			else
				queue.push(0);
			cmdnum++;
		}
			break;

		case '%': {
			x = queue.pop();
			y = queue.pop();
			if (y != 0)
				queue.push(x % y);
			else
				queue.push(0);
			cmdnum++;
		}
			break;

		case '>': {
			registers[curcmd[1] - 'a'] = queue.pop();
			cmdnum++;
		}
			break;

		case '<': {
			queue.push(registers[curcmd[1] - 'a']);
			cmdnum++;
		}
			break;

		case 'P': {
			if (curcmd.length() == 1) {
				x = queue.pop();
				sout << x << endl;
			} else {
				sout << registers[curcmd[1] - 'a'] << endl;
			}
			cmdnum++;
		}
			break;

		case 'C': {
			if (curcmd.length() == 1) {
				x = queue.pop();
				sout << (char) (x & 255);
			} else {
				sout << (char) (registers[curcmd[1] - 'a'] & 255);
			}
			cmdnum++;
		}
			break;
		case 'J': {
			//cmdnum = atoi(curcmd.substr(1, curcmd.length() - 1).c_str());
			cmdnum=getline(curcmd.substr(1));
		}
			break;
		case 'Z': {
			if (registers[curcmd[1] - 'a'] == 0)
				//cmdnum = atoi(curcmd.substr(2).c_str());
				cmdnum=getline(curcmd.substr(2));
			else
				cmdnum++;
		}
			break;
		case 'E': {
			if (registers[curcmd[1] - 'a'] == registers[curcmd[2] - 'a'])
			//	cmdnum = atoi(curcmd.substr(3).c_str());
				cmdnum=getline(curcmd.substr(3));
			else
				cmdnum++;
		}
			break;
		case 'G': {
			if (registers[curcmd[1] - 'a'] > registers[curcmd[2] - 'a'])
			//	cmdnum = atoi(curcmd.substr(3).c_str());
				cmdnum=getline(curcmd.substr(3));
			else
				cmdnum++;
		}
			break;
		case 'Q':
			isrunning = false;
			break;
		case ':':
					cmdnum++;
					break;
		default: {
			queue.push(atoi(curcmd.c_str()));
			cmdnum++;
		}break;

		}
	}
	sin.close();
	sout.close();
	return 0;
}
