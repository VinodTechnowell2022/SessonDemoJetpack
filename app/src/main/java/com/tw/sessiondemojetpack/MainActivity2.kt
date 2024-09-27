package com.tw.sessiondemojetpack

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tw.sessiondemojetpack.ui.theme.SessionDemoJetpackTheme
import com.tw.sessiondemojetpack.ui.theme.greenColor

class MainActivity2 : ComponentActivity() {

    // on below line we are creating a variable for our shared preferences.
    private lateinit var sharedPreferences: SharedPreferences

    // on below line we are creating a variable
    // for prefs key and email key and pwd key.
    private var PREFS_KEY = "prefs"
    private var EMAIL_KEY = "email"

    // on below line we are creating a variable for email
    private var email = ""

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // on below line we are initializing our shared preferences.
        sharedPreferences = getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)

        // on below line we are initializing our email from shared preferences.
        email = sharedPreferences.getString(EMAIL_KEY, "").toString()

        setContent {
            SessionDemoJetpackTheme {
                // on below line we are specifying
                // background color for our application
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // on below line we are specifying theme as scaffold.
                    Scaffold(
                        // in scaffold we are specifying top bar.
                        topBar = {
                            // inside top bar we are specifying background color.
                            TopAppBar(colors = TopAppBarDefaults.topAppBarColors(),
                                // along with that we are specifying title for our top bar.
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
                        Log.e(this.javaClass.simpleName, "onCreate: $innerPadding", )

                        // on below line we are calling get data and passing email and shared preferences to it.
                        getData(email, sharedPreferences)
                    }
                }
            }
        }
    }
}

// on below line we are creating a get data method.
@Composable
fun getData(email: String, sharedPreferences: SharedPreferences) {
    // on below line we are creating and initializing
    // our context variable and activity.
    val ctx = LocalContext.current
    val activity = (LocalContext.current as? Activity)

    // on below line we are creating a column
    Column(
        // inside the column we are adding modifier
        // and specifying max height, max width and max size.
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .fillMaxSize(),

        // on below line we are specifying
        // vertical arrangement for our column.
        verticalArrangement = Arrangement.Center,

        // on below line we are specifying horizontal alignment for our column.
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // on below line we are creating a text for displaying user name
        Text(
            // on below line we are specifying text in our text
            text = "Welcome \n$email",

            // on below line we are specifying font weight for our text
            fontWeight = FontWeight.Bold,

            // on below line we are adding green color to it.
            color = greenColor,

            // on below line we are specifying font size to it.
            fontSize = 20.sp,

            // on below line we are adding alignment for our text
            textAlign = TextAlign.Center
        )

        // on below line we are adding spacer between text and button.
        Spacer(modifier = Modifier.height(50.dp))

        // on below line we are adding button.
        Button(

            // on below line we are adding modifier to
            // fill max width and adding padding to it.
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            // on below line we are adding on click for our button.
            onClick = {

                // on below line we are creating a variable for our editor.
                val editor: SharedPreferences.Editor = sharedPreferences.edit()

                // on below line we are passing email and pwd with empty values.
                editor.putString("email", "")
                editor.putString("pwd", "")

                // on below line we are applying changes which are updated.
                editor.apply()

                // on below line we are opening our main activity.
                val i = Intent(ctx, MainActivity::class.java)
                ctx.startActivity(i)
                activity?.finish()
            },
            // on below line we are adding color for out button
            colors = ButtonDefaults.buttonColors(
                containerColor = greenColor,
            )
        ) {
            // on below line we are adding text for our button.
            Text(text = "Log Out")
        }

    }
}