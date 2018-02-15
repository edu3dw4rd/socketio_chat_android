//package com.example.edward.socketiochat
//
//import android.content.Intent
//import android.support.v7.app.AppCompatActivity
//import android.os.Bundle
//import android.text.TextUtils
//import android.util.Log
//import android.view.KeyEvent
//import android.view.View
//import android.view.inputmethod.EditorInfo
//import android.widget.Button
//import android.widget.EditText
//import android.widget.TextView
//import io.socket.client.Socket
//import io.socket.emitter.Emitter
//import org.json.JSONException
//import org.json.JSONObject
//
//class LoginActivities : AppCompatActivity() {
//
//    private lateinit var mUsernameView: EditText;
//    private var mUserName: String ?= null;
//    private var mSocket: Socket ?= null;
//
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//
//        var app: ChatApplications = application as ChatApplications;
//
//        mSocket = app.getSocket();
//Log.d("Test", mSocket.toString());
//        // Set login form
//        mUsernameView = findViewById<EditText>(R.id.username_input);
//        Log.d("Test", "hmmm");
//        Log.d("Test", mUsernameView.toString());
//        mUsernameView.setOnEditorActionListener(object:TextView.OnEditorActionListener{
//            override fun onEditorAction(textView: TextView?, id: Int, keyEvent: KeyEvent?): Boolean {
//                Log.d("Test", "imahere");
//                if(id == R.integer.custom_login_id || id == EditorInfo.IME_NULL){
//                    Log.d("Test", "hello");
//                    attemptLogin();
//
//                    return true;
//                }
//
//                return false;
//            }
//        });
//
//        var signInButton: Button = findViewById<Button>(R.id.sign_in_button);
//        signInButton.setOnClickListener(object: View.OnClickListener{
//            override fun onClick(p0: View?) {
//                Log.d("Test", "Clicked");
//                attemptLogin();
//            }
//        });
//
//        mSocket?.on("new user", onLogin);
//    }
//
//    fun attemptLogin(){
//        mUsernameView.setError(null);
//
//        var userName: String = mUsernameView.text.toString().trim();
//        Log.d("Test", userName);
//
//        if(TextUtils.isEmpty(userName)){
//            mUsernameView.setError(getString(R.string.error_field_required));
//            mUsernameView.requestFocus();
//
//            return;
//        }
//
//        mUserName = userName;
//
//        //perform the user login attempt
//        mSocket?.emit("new user", userName);
//    }
//
//    var onLogin : Emitter.Listener = object: Emitter.Listener {
//        override fun call(vararg args: Any?) {
//            var data : JSONObject = args[0] as JSONObject;
//
//            var numUsers: Int ?= null;
//
//            try{
//                numUsers = data.getInt("numUsers");
//            } catch (e : JSONException ){
//                Log.d("ErrorLogin", e.toString());
//                return;
//            }
//
//            Log.d("IntentPrepare", "yuhuu");
//            var intent: Intent = Intent();
//            intent.putExtra("username", mUserName);
//            intent.putExtra("numUsers", numUsers);
//            setResult(RESULT_OK, intent);
//            finish();
//        }
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//
//    }
//
//}
