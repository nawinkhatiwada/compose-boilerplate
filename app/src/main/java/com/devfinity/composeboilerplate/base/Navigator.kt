package com.devfinity.composeboilerplate.base

import com.devfinity.composeboilerplate.routes.Screen

interface Navigator {
    var isLoading: Boolean?
    var data: Any?
    var hasError: Boolean?
    var errorMessage: String?
    var navigateTo: Screen?
}