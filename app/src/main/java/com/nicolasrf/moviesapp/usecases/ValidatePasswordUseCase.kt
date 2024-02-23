package com.nicolasrf.moviesapp.usecases

import java.util.regex.Pattern

class ValidatePasswordUseCase {
    operator fun invoke(password: String): PasswordResult {
        if (password.length < 8) {
            return PasswordResult.INVALID_LENGTH
        }

        if (!password.any { it.isLowerCase() }) {
            return PasswordResult.INVALID_LOWERCASE
        }

        if (!password.any { it.isUpperCase() }) {
            return PasswordResult.INVALID_UPPERCASE
        }

        if (!password.any { it.isDigit() }) {
            return PasswordResult.INVALID_DIGITS
        }

        val exp = ".*[~!@#\$%\\^&*()\\-_=+\\|\\[{\\]};:'\",<.>/?].*"
        val pattern = Pattern.compile(exp)
        val matcher = pattern.matcher(password)
        if (!matcher.matches()) {
            return PasswordResult.INVALID_SPECIAL
        }

        return PasswordResult.VALID
    }
}

enum class PasswordResult {
    VALID,
    INVALID_LOWERCASE,
    INVALID_UPPERCASE,
    INVALID_DIGITS,
    INVALID_LENGTH,
    INVALID_SPECIAL
}
