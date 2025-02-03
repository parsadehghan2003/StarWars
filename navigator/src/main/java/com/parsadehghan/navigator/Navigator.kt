package com.parsadehghan.navigator;

import android.os.Bundle

interface Navigator {
    fun navigateTo(destination: String, args: Bundle? = null)
}
