#include <bits/stdc++.h>
using namespace std;

stack<int> s;

bool isOperation(string str) {
    return str == "+" || str == "-" || str == "/" || str == "*";
}

bool isNumber(char c) {
    return c >= '0' && c <= '9';
}

bool haveANumber(string str) {
    for (char c : str) {
        if (isNumber(c)) return true;
    }

    return false;
}

bool saveNumber(string str) {
    if (haveANumber(str)) {
        s.push(stoi(str));
        return true;
    }
    return false;
}

void resolveOperation(string str) {
    if (s.size() >= 2) {
        int a = s.top(); s.pop();
        int b = s.top(); s.pop();

        int result;
        if (str == "+") result = b+a;
        if (str == "-") result = b-a;
        if (str == "*") result = b*a;
        if (str == "/") result = a == 0 ? b : b/a;

        //cout << a << " " << str << " " << b << " = " << result << endl; 
        s.push(result);
    }
}

int main() {
    ifstream file;
    file.open("Calc1.stk");

    string input;
    int i = 0;
    while(!file.eof()) {
        i++;
        file >> input;
        //cout << input << endl;
        if (isOperation(input)) {
            resolveOperation(input);
        } else {
            if (!saveNumber(input)) {
                cout << "Entrada inválida na linha " << i << endl;
                cout << "Encerrando programa" << endl;

                file.close();
                return 0;
            }
        }
    }

    cout << "Saída: " << s.top() << endl;

    file.close();
    return 0;
}