//
// Created by Albert Kyei on 4/28/2019.
//

#include <jni.h>
#include <string>
#include <jni.h>
#include <string>
#include <cstddef>
#include <fstream>
using namespace std;

extern "C" JNIEXPORT jboolean JNICALL
Java_com_example_repsyche_SignUpPage_signUp(JNIenv *env, jobject, jstring user_details){

    ofstream database_file;
    database_file.open("Database.txt", ios::app)

    //send error back to caller if the file stream was successful opening the file.
    if (!database_file.is_open()) { return false; }

    database_file << user_details << "\n";

    database_file.close();

    return true;

}

extern "C" JNIEXPORT jboolean JNICALL
Java_com_example_repsyche_SignUpPage_newUser(JNIenv *env, jobject, jstring user_name, jstring email){

    string line;
    ofstream database_file;
    database_file.open("Database.txt", ios::in)

    //send error back to caller if the file stream was successful opening the file.
    if (!database_file.is_open()) { return false; }

    while ( getline(myfile,line) )
    {
        //Split line to obtain username and email of user detail at current line
        //Check if username and email already exist
        //If so return false
        cout << line << '\n';
    }

    database_file.close();

    return true;

}