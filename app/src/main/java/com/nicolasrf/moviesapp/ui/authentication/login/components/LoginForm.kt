package com.nicolasrf.moviesapp.ui.authentication.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nicolasrf.moviesapp.ui.authentication.login.LoginEvent
import com.nicolasrf.moviesapp.ui.authentication.login.LoginState

@Composable
fun LoginForm(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.background(Color.White, shape = RoundedCornerShape(20.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Log in with Email",
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.tertiary
            )
            Divider(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                color = MaterialTheme.colorScheme.background
            )
            CustomTextfield(
                value = state.email,
                onValueChange = { onEvent(LoginEvent.EmailChange(it)) },
                placeholder = "Email",
                contentDescription = "Enter email",
                modifier = Modifier.fillMaxWidth().padding(bottom = 6.dp).padding(horizontal = 20.dp),
                leadingIcon = Icons.Outlined.Email,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onAny = {
                    focusManager.moveFocus(FocusDirection.Next)
                }),
                errorMessage = state.emailError,
                isEnabled = !state.isLoading
            )

            CustomPasswordTextfield(
                value = state.password,
                onValueChange = { onEvent(LoginEvent.PasswordChange(it)) },
                contentDescription = "Enter password",
                modifier = Modifier.fillMaxWidth().padding(bottom = 6.dp).padding(horizontal = 20.dp),
                errorMessage = state.passwordError,
                isEnabled = !state.isLoading,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onAny = {
                    focusManager.clearFocus()
                    onEvent(LoginEvent.Login)
                })
            )
            CustomButton(
                text = "Login",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                isEnabled = !state.isLoading
            ) {
                onEvent(LoginEvent.Login)
            }
        }
        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }
}

@Preview
@Composable
fun LoginFormPreview() {
    LoginForm(LoginState(), {})
}
