package com.nicolasrf.moviesapp.domain.matcher

interface EmailMatcher {
    fun isValid(email: String): Boolean
}