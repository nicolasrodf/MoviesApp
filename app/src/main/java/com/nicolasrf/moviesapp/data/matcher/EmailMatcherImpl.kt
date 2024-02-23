package com.nicolasrf.moviesapp.data.matcher

import android.util.Patterns
import com.nicolasrf.moviesapp.domain.matcher.EmailMatcher

class EmailMatcherImpl : EmailMatcher {
    override fun isValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}