package com.tw.sessiondemojetpack

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tw.sessiondemojetpack.ui.theme.SessionDemoJetpackTheme
import com.tw.sessiondemojetpack.ui.theme.greenColor

class MainActivity : ComponentActivity() {

    // on below line we are creating
    // a variable for shared preferences.
    lateinit var sharedPreferences: SharedPreferences

    // on below line we are creating a variable
    // for prefs key and email key and pwd key.
    var PREFS_KEY = "prefs"
    var EMAIL_KEY = "email"
    var PWD_KEY = "pwd"

    // on below line we are creating variable
    // for email as e and password as p.
    var e = ""
    var p = ""


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // on below line we are initializing our shared preferences.
            sharedPreferences = getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)

            // on below line we are creating
            // variable for email and password.
            val email = remember {
                mutableStateOf("")
            }
            val pwd = remember {
                mutableStateOf("")
            }

            // on below line we are initializing values for both email and password.
            email.value = sharedPreferences.getString(EMAIL_KEY, "").toString()
            pwd.value = sharedPreferences.getString(PWD_KEY, "").toString()

            // on below line we are setting value
            // from email and password to e and p
            e = email.value
            p = pwd.value


            SessionDemoJetpackTheme {
                // on below line we are specifying background
                // color for our application
                Surface(
                    // on below line we are specifying
                    // modifier and color for our app
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    // on below line we are specifying theme as scaffold.
                    Scaffold(

                        // in scaffold we are specifying top bar.
                        topBar = {

                            // inside top bar we are specifying background color.
                            TopAppBar(colors = TopAppBarDefaults.topAppBarColors(),

                                // along with that we are specifying title
                                // for our top bar.
                                title = {

                                    // in the top bar we are specifying tile as a text
                                    Text(

                                        // on below line we are specifying text to display in top app bar.
                                        text = "Session Management in Android",

                                        // on below line we are specifying modifier to fill max width.
                                        modifier = Modifier.fillMaxWidth(),

                                        // on below line we are specifying text alignment.
                                        textAlign = TextAlign.Center,

                                        // on below line we are specifying color for our text.
                                        color = Color.White
                                    )
                                }
                            )
                        }
                    ) { innerPadding ->
                        Log.e(this.javaClass.simpleName, "onCreate: "+innerPadding)

                        // on below line we are calling session management
                        // method and passing shared preferences to it.
                        sessionManagement(sharedPreferences)
                    }
                }
            }
        }

    }

    // on below line we are calling on start method.
    override fun onStart() {
        super.onStart()
        // on below line we are creating a variable for activity.
        val activity = (this as? Activity)

        // on below line we are initializing our shared preferences.
        sharedPreferences = getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)

        // on below line we are initializing our email and pwd
        // variable setting values from shared preferences.
        val email = sharedPreferences.getString(EMAIL_KEY, "").toString()
        val pwd = sharedPreferences.getString(PWD_KEY, "").toString()

        // on below line we are checking if email and pwd are empty or not.
        if (email != "" && pwd != "") {
            // if email and pwd are not empty we are opening
            // a new activity on below line.
            val i = Intent(this, MainActivity2::class.java)

            // on below line we are starting our new activity
            // and finishing our current activity.
            startActivity(i)
            activity?.finish()
        }
    }
}

// on below line we are creating a function for session management
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun sessionManagement(sharedPreferences: SharedPreferences) {

    // on below line we are creating a variable for
    // context and activity and initializing it.
    val ctx = LocalContext.current
    val activity = (LocalContext.current as? Activity)

    // on below line we are creating and
    // initializing email value and pwd value
    val emailValue = remember {
        mutableStateOf(TextFieldValue())
    }
    val pwdValue = remember {
        mutableStateOf(TextFieldValue())
    }

    // on the below line we are creating a column.
    Column(
        // on below line we are adding a modifier to it
        // and setting max size, max height and max width
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .fillMaxWidth(),

        // on below line we are adding vertical
        // arrangement and horizontal alignment.
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // on below line we are creating a text
        Text(
            // on below line we are specifying text as
            // Session Management in Android.
            text = "Session Management in Android",

            // on below line we are specifying text color.
            color = greenColor,

            // on below line we are specifying font family
            fontFamily = FontFamily.Default,

            // on below line we are adding font weight
            // and alignment for our text
            fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
        )

        // on below line we are creating a text field for our email.
        TextField(
            // on below line we are specifying value for our email text field.
            value = emailValue.value,

            // on below line we are adding on value change for text field.
            onValueChange = { emailValue.value = it },

            // on below line we are adding place holder as text as "Enter your email"
            placeholder = { Text(text = "Enter your email") },

            // on below line we are adding modifier to it
            // and adding padding to it and filling max width
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),

            // on below line we are adding text style
            // specifying color and font size to it.
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),

            // on below line we are adding single line to it.
            singleLine = true,

            // on below line we are specifying leading icon as email
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "Email")
            }
        )

        // on below line we are creating a text field for password.
        TextField(
            // on below line we are specifying value for our password as pwdValue.
            value = pwdValue.value,

            // on below line we are specifying on value change for it.
            onValueChange = { pwdValue.value = it },

            // on below line we are adding place holder to it as enter your password.
            placeholder = { Text(text = "Enter your password") },

            // on below line we are adding modifier to it and padding to it.
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),

            // on below line we are adding text style for our text and font size.
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),

            // on below line we are adding single line to it.
            singleLine = true,

            // on below line we are adding visual
            // transformation it obscure the password.
            visualTransformation = PasswordVisualTransformation(),

            // on below line we are adding leading icon to it.
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "Email")
            }
        )

        // on the below line we are adding a button for login.
        Button(
            // on below line we are adding modifier to
            // it for adding padding and max width.
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            // on below line we are adding on click to this button.
            onClick = {
                // on below line we are calling save data method
                // to save data to shared preferences.
                saveData(emailValue.value.text, pwdValue.value.text, sharedPreferences, ctx)

                // on the below line we are calling finish to close the current activity.
                activity?.finish()
            },
            // on below line we are adding color to our button.
            colors = ButtonDefaults.buttonColors(
                contentColor = greenColor,
            )
        )
        // on the below line we are adding text as login.
        {
            Text(text = "Login")
        }
    }
}

// on below line we are creating a function as save data
// to save data in our shared preferences.
fun saveData(email: String, pwd: String, sharedPreferences: SharedPreferences, context: Context) {
    // on below line we are creating an editor and initializing
    // it with shared preferences.
    val editor: SharedPreferences.Editor = sharedPreferences.edit()

    // on below line we are setting email and pwd value with key.
    editor.putString("email", email)
    editor.putString("pwd", pwd)

    // on the below line we are applying
    // changes to our shared prefs.
    editor.apply()

    // on below line we are opening a new intent.
    val i = Intent(context, MainActivity2::class.java)
    context.startActivity(i)
}