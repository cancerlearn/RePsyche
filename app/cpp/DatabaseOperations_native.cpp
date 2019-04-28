//
// Created by Albert Kyei on 4/28/2019.
//

#include <jni.h>
#include <string>
#include <jni.h>
#include <string>
#include <cstddef>
#include <fstream>
#include <stdlib.h>
using namespace std;

extern "C" JNIEXPORT jboolean JNICALL
Java_com_example_repsyche_SignUpPage_signUp(JNIEnv *env, jobject, jstring user_details) {

    string cpath = "database.txt";
    ofstream database_file(cpath, ios::out | ios::app);

    if (database_file.is_open()) {
        database_file << "yadayada" << std::endl;

        database_file.close();

        return true;
    }
//    jboolean isCopy;
//    const char *convertedValue = (env)->GetStringUTFChars(user_details, &isCopy);

    return false;

}

extern "C" JNIEXPORT jboolean JNICALL
Java_com_example_repsyche_SignUpPage_newUser(JNIEnv *env, jobject, jstring user_name, jstring email){

    string line; //lines in database text file
    ifstream database_file ("app\\Database.txt");

    if (!database_file.is_open()) { //here if new file is created
        ifstream database_file ("app\\Database.txt", ios::in | ios::out | ios::trunc);
        return true;
    }

    else if (database_file.peek() == std::ifstream::traits_type::eof()){ //here if file exist but is empty
        return true;
    }

    while ( getline(database_file, line) )
    {
        string delimiter = " | "; //Delimiter to split user_details line at.
        size_t pos = 0; //Position of tokens obtained after splitting.
        string token; //Token obtained after splitting.
        int userdetail_pos = 0;

        while((pos = line.find(delimiter)) != std::string::npos){ //While the delimiter is still being found, continue
            token = line.substr(0, pos);
            line.erase(0, pos + delimiter.length());

            if (userdetail_pos == 2){
                jstring jtoken = env->NewStringUTF(token.c_str());

                jclass cls = env->GetObjectClass(jtoken);
                jmethodID mID = env->GetMethodID(cls, "equals", "(Ljava/lang/Object;)Z");
                jboolean equal_tokens = env->CallBooleanMethod(jtoken, mID, user_name);

                database_file.close();
                return equal_tokens;
                }

            if (userdetail_pos == 3){
                jstring jtoken = env->NewStringUTF(token.c_str());

                jclass cls = env->GetObjectClass(jtoken);
                jmethodID mID = env->GetMethodID(cls, "equals", "(Ljava/lang/Object;)Z");
                jboolean equal_tokens = env->CallBooleanMethod(jtoken, mID, email);

                database_file.close();
                return equal_tokens;
                }

            userdetail_pos++;
            }
        }
    database_file.close();
    return true;
}

extern "C" JNIEXPORT jboolean JNICALL
Java_com_example_repsyche_LoginPage_confirmUser(JNIEnv *env, jobject, jstring username, jstring password){
    return false;
}