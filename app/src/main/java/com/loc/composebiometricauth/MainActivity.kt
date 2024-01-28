package com.loc.composebiometricauth

import android.app.Activity
import android.hardware.biometrics.BiometricPrompt
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import com.loc.composebiometricauth.ui.theme.ComposeBiometricAuthTheme

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val biometricAuthenticator = BiometricAuthenticator(this)

        setContent {
            ComposeBiometricAuthTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val activity = LocalContext.current as FragmentActivity
                    var message by remember {
                        mutableStateOf("")
                    }
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TextButton(
                            onClick = {
                                biometricAuthenticator.promptBiometricAuth(
                                    title = "Login",
                                    subTitle = "Use your fingerprint",
                                    negativeButtonText = "Cancel",
                                    fragmentActivity = activity,
                                    onSuccess = {
                                        message = "Success"
                                    },
                                    onError = { _, errorString ->
                                        message = errorString.toString()
                                    },
                                    onFailed = {
                                        message = "Verification error"
                                    }
                                )
                            }) {
                            Text(text = "Sign in with fingerprint")
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = message)
                    }
                }
            }
        }
    }
}