package com.nicolasrf.moviesapp.usecases

import com.nicolasrf.moviesapp.domain.matcher.EmailMatcher

class ValidateEmailUseCase(private val emailMatcher: EmailMatcher) {
    operator fun invoke(email: String): Boolean {
        return emailMatcher.isValid(email)
    }
}
