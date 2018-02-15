//package com.example.edward.socketiochat
//
//import android.app.Activity
//import android.content.Context
//import android.content.Intent
//import android.net.Uri
//import android.os.Bundle
//import android.os.Handler
//import android.support.v4.app.Fragment
//import android.support.v7.widget.LinearLayoutManager
//import android.support.v7.widget.RecyclerView
//import android.text.Editable
//import android.text.TextUtils
//import android.text.TextWatcher
//import android.util.Log
//import android.view.*
//import android.view.inputmethod.EditorInfo
//import android.widget.EditText
//import android.widget.ImageButton
//import android.widget.TextView
//import android.widget.Toast
//import io.socket.client.Socket
//import io.socket.emitter.Emitter
//import org.json.JSONException
//import org.json.JSONObject
//
//
///**
// * A simple [Fragment] subclass.
// * Activities that contain this fragment must implement the
// * [MainFragments.OnFragmentInteractionListener] interface
// * to handle interaction events.
// * Use the [MainFragments.newInstance] factory method to
// * create an instance of this fragment.
// */
//class MainFragments : Fragment() {
//
//    // TODO: Rename and change types of parameters
//    private lateinit var mParam1: String
//    private lateinit var mParam2: String
//
//    private lateinit var mListener: OnFragmentInteractionListener
//
//    private lateinit var mMessagesView: RecyclerView;
//    private lateinit var mInputMessageView: EditText;
//    private var mMessages: MutableList<Messages> = mutableListOf<Messages>();
//    private lateinit var mAdapter: RecyclerView.Adapter<MessageAdapters.ViewHolder>;
//    private var mTyping: Boolean = false;
//    private var mTypingHandler: Handler = Handler();
//    private lateinit var mUsername: String;
//    private lateinit var mSocket: Socket;
//
//    private var isConnected: Boolean = true;
//
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
////        if (arguments != null) {
////            mParam1 = arguments.getString(ARG_PARAM1)
////            mParam2 = arguments.getString(ARG_PARAM2)
////        }
//
//        setHasOptionsMenu(true);
//
//        var app: ChatApplications = activity.application as ChatApplications;
////        mSocket = ChatApplications.socket;
//        mSocket = app.getSocket();
//        mSocket?.on(Socket.EVENT_CONNECT, onConnect);
//        mSocket?.on(Socket.EVENT_DISCONNECT, onDisconnect);
//        mSocket?.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
//        mSocket?.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
//        mSocket?.on("new message", onNewMessage);
//        mSocket?.on("user joined", onUserJoined);
//        mSocket?.on("user left", onUserLeft);
//        mSocket?.on("typing", onTyping);
//        mSocket?.on("stop typing", onStopTyping);
//        mSocket?.connect();
//
//        startSignIn();
//    }
//
//    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
//                              savedInstanceState: Bundle?): View? {
//        // Inflate the layout for this fragment
//        return inflater!!.inflate(R.layout.fragment_main, container, false)
//    }
//
//    // TODO: Rename method, update argument and hook method into UI event
//    fun onButtonPressed(uri: Uri) {
//        if (mListener != null) {
//            mListener!!.onFragmentInteraction(uri)
//        }
//    }
//
//    override fun onAttach(context: Context?) {
//        super.onAttach(context)
//
//        mAdapter = MessageAdapters(context as Context, mMessages);
//
//        if (context is OnFragmentInteractionListener) {
////            mListener = context
//        } else {
////            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
//        }
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        mListener = null
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     *
//     *
//     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
//     */
//    interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        fun onFragmentInteraction(uri: Uri)
//    }
//
//    companion object {
//        // TODO: Rename parameter arguments, choose names that match
//        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//        private final val TAG: String = "MainFragments";
//        private final val REQUEST_LOGIN: Int = 0;
//        private final val TYPING_TIMER_LENGTH: Long = 600;
//
//        private val ARG_PARAM1 = "param1"
//        private val ARG_PARAM2 = "param2"
//
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment MainFragments.
//         */
//        // TODO: Rename and change types and number of parameters
//        fun newInstance(param1: String, param2: String): MainFragments {
//            val fragment = MainFragments()
//            val args = Bundle()
//            args.putString(ARG_PARAM1, param1)
//            args.putString(ARG_PARAM2, param2)
//            fragment.arguments = args
//            return fragment
//        }
//    }
//
//    private var onConnect: Emitter.Listener = object: Emitter.Listener {
//        override fun call(vararg args: Any?) {
//            activity.runOnUiThread(object :Runnable {
//                override fun run() {
//                    Log.i("TestLog", "imahere");
//                    if(!isConnected){
//                        if(mUsername != null){
//                            mSocket?.emit("new user", mUsername);
//                        }
//
//                        Toast.makeText(activity.applicationContext, R.string.connect, Toast.LENGTH_LONG).show();
//
//                        isConnected = true;
//                    }
//                }
//
//            });
//        }
//    }
//
//
//    private var onDisconnect: Emitter.Listener = object: Emitter.Listener {
//        override fun call(vararg args: Any?) {
//            activity.runOnUiThread(object : Runnable {
//                override fun run() {
//                    Log.i(TAG, "disconnected");
//                    isConnected = false;
//
//                    Toast.makeText(activity.applicationContext, R.string.disconnect, Toast.LENGTH_LONG).show();
//                }
//            });
//        }
//    }
//
//    private var onConnectError: Emitter.Listener = object: Emitter.Listener{
//        override fun call(vararg args: Any?) {
//            activity.runOnUiThread(object : Runnable {
//                override fun run() {
//                    Log.e(TAG, "Error connecting");
//                    Toast.makeText(activity.applicationContext, R.string.error_connect, Toast.LENGTH_LONG).show();
//                }
//            });
//        }
//    }
//
//    private var onNewMessage: Emitter.Listener = object: Emitter.Listener{
//        override fun call(vararg args: Any?) {
//            activity.runOnUiThread(object: Runnable{
//                override fun run() {
//                    var data: JSONObject = args[0] as JSONObject;
//                    var username: String;
//                    var message: String;
//
//                    try {
//                        username = data.getString("username");
//                        message = data.getString("message");
//                    } catch(e: JSONException){
//                        Log.e(TAG, e.message);
//                        return;
//                    }
//
//                    removeTyping(username);
//                    addMessage(username, message);
//                }
//            })
//        }
//    }
//
//    private var onUserJoined: Emitter.Listener = object:Emitter.Listener{
//        override fun call(vararg args: Any?) {
//            activity.runOnUiThread(object: Runnable{
//                override fun run() {
//                    var data: JSONObject = args[0] as JSONObject;
//                    var username: String;
//                    var numUsers: Int;
//
//                    try{
//                        username = data.getString("username");
//                        numUsers = data.getInt("numUsers");
//                    } catch (e: JSONException){
//                        Log.e(TAG, e.message);
//                        return;
//                    }
//
//                    addLog(resources.getString(R.string.message_user_joined, username));
//                    addParticipantsLog(numUsers);
//                }
//            })
//        }
//    }
//
//    private var onUserLeft: Emitter.Listener = object : Emitter.Listener{
//        override fun call(vararg args: Any?) {
//            activity.runOnUiThread(object: Runnable{
//                override fun run() {
//                    var data: JSONObject = args[0] as JSONObject;
//                    var username: String;
//                    var numUsers: Int;
//
//                    try {
//                        username = data.getString("username");
//                        numUsers = data.getInt("numUsers");
//                    } catch (e: JSONException){
//                        Log.e(TAG, e.message);
//                        return;
//                    }
//
//                    addLog(resources.getString(R.string.message_user_left, username));
//                    addParticipantsLog(numUsers);
//                    removeTyping(username);
//                }
//            })
//        }
//    }
//
//    private var onTyping: Emitter.Listener = object: Emitter.Listener{
//        override fun call(vararg args: Any?) {
//            activity.runOnUiThread(object: Runnable{
//                override fun run() {
//                    var data: JSONObject = args[0] as JSONObject;
//                    var username: String;
//
//                    try{
//                        username = data.getString("username");
//                    }catch (e: JSONException){
//                        Log.e(TAG, e.message);
//                        return;
//                    }
//
//                    addTyping(username);
//                }
//            })
//        }
//    }
//
//    private var onStopTyping: Emitter.Listener = object: Emitter.Listener{
//        override fun call(vararg args: Any?) {
//            activity.runOnUiThread(object: Runnable{
//                override fun run() {
//                    var data: JSONObject = args[0] as JSONObject;
//                    var username: String;
//
//                    try {
//                        username = data.getString("username");
//                    }catch (e: JSONException){
//                        Log.e(TAG, e.message);
//                        return;
//                    }
//
//                    removeTyping(username);
//                }
//            })
//        }
//    }
//
//    private var onTypingTimeout: Runnable = object: Runnable {
//        override fun run() {
//            if(!mTyping){
//                return;
//            }
//
//            mTyping = false;
//            mSocket?.emit("stopTyping");
//        }
//    }
//
//    override public fun onDestroy() {
//        super.onDestroy();
//
//        mSocket?.disconnect();
//
//        mSocket?.off(Socket.EVENT_CONNECT, onConnect);
//        mSocket?.off(Socket.EVENT_DISCONNECT, onDisconnect);
//        mSocket?.off(Socket.EVENT_CONNECT_ERROR, onConnectError);
//        mSocket?.off(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
//        mSocket?.off("new message", onNewMessage);
//        mSocket?.off("user joined", onUserJoined);
//        mSocket?.off("user left", onUserLeft);
//        mSocket?.off("typing", onTyping);
//        mSocket?.off("stop typing", onStopTyping);
//    }
//
//    override public fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState);
//
//        mMessagesView = view?.findViewById<RecyclerView>(R.id.messages) as RecyclerView;
//        mMessagesView.setLayoutManager(LinearLayoutManager(activity));
//        mMessagesView.adapter = mAdapter;
//
//        mInputMessageView = view.findViewById<EditText>(R.id.message_input);
//        mInputMessageView.setOnEditorActionListener(object: TextView.OnEditorActionListener{
//            override fun onEditorAction(v: TextView?, id: Int, event: KeyEvent?): Boolean {
//                if(R.integer.custom_send_id == id || EditorInfo.IME_NULL == id){
//                    attemptSend();
//                    return true;
//                }
//
//                return false;
//            }
//        });
//
//        mInputMessageView.addTextChangedListener(object: TextWatcher{
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                if(mUsername == null){
//                    return;
//                }
//
//                if(!(mSocket!!.connected())){
//                    return;
//                }
//
//                if(!mTyping){
//                    mTyping = true;
//                    mSocket?.emit("typing");
//                }
//
//                mTypingHandler.removeCallbacks(onTypingTimeout);
//                mTypingHandler.postDelayed(onTypingTimeout, TYPING_TIMER_LENGTH);
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//            }
//        });
//
//        var sendButton:ImageButton = view.findViewById<ImageButton>(R.id.send_button);
//        sendButton.setOnClickListener(object: View.OnClickListener{
//            override fun onClick(v: View?) {
//                attemptSend();
//            }
//        });
//
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(Activity.RESULT_OK != resultCode){
//            activity.finish();
//            return;
//        }
//
//        mUsername = data?.getStringExtra("username");
//        var numUsers: Int = data!!.getIntExtra("numUsers", 1);
//
//        addLog(resources.getString(R.string.message_welcome));
//        addParticipantsLog(numUsers);
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
//        super.onCreateOptionsMenu(menu, inflater)
//
//        inflater?.inflate(R.menu.menu_main, menu);
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        var id: Int = item!!.itemId;
//
//        if(R.id.action_leave == id){
//            leave();
//
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item)
//    }
//
//    private fun addLog(message: String){
//        mMessages.add(Messages.Builder(Messages.TYPE_LOG).message(message).build());
//        mAdapter?.notifyItemInserted(mMessages.size - 1);
//    }
//
//    private fun addParticipantsLog(numUsers: Int){
//        addLog(resources.getQuantityString(R.plurals.message_participants, numUsers, numUsers));
//    }
//
//    private fun addMessage(username: String, message: String){
//        mMessages.add(Messages.Builder(Messages.TYPE_MESSAGE).username(username).message(message).build());
//        mAdapter?.notifyItemInserted(mMessages.size - 1);
//        scrollToBottom();
//    }
//
//    private fun addTyping(username: String){
//        mMessages.add(Messages.Builder(Messages.TYPE_ACTION).username(username).build());
//        mAdapter?.notifyItemInserted(mMessages.size - 1);
//
//        scrollToBottom();
//    }
//
//    private fun removeTyping(username: String){
//        for(i in mMessages.size - 1 .. 0){
//            var message: Messages = mMessages.get(i);
//
//            if(Messages.TYPE_ACTION == message.getType() && message.getUsername().equals(username)){
//                mMessages.removeAt(i);
//                mAdapter?.notifyItemRemoved(i);
//            }
//        }
//    }
//
//    private fun attemptSend(){
//        var username = mUsername;
//
//        if(username == null) return;
//
//        if(!(mSocket!!.connected())) return;
//
//        mTyping = false;
//
//        var message: String = mInputMessageView.text.toString().trim();
//
//        if(TextUtils.isEmpty(message)){
//            mInputMessageView.requestFocus();
//            return;
//        }
//
//        mInputMessageView.setText("");
//        addMessage(username, message);
//
//        mSocket?.emit("new message", message);
//    }
//
//    private fun startSignIn(){
//        mUsername = null;
//        var intent: Intent = Intent(activity, LoginActivities::class.java);
//        startActivityForResult(intent, REQUEST_LOGIN);
//    }
//
//    private fun leave(){
//        mUsername = null;
//        mSocket?.disconnect();
//        mSocket?.connect();
//        startSignIn();
//    }
//
//    private fun scrollToBottom(){
//        mMessagesView.scrollToPosition(mAdapter!!.itemCount - 1);
//    }
//}// Required empty public constructor
