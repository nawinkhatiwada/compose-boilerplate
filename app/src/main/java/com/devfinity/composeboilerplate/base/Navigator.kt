package com.devfinity.composeboilerplate.base

import com.devfinity.composeboilerplate.routes.Screen

interface Navigator {
    var navigateTo: Screen?
}