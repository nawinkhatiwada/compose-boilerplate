package com.devfinity.composeboilerplate.utils

/**
 * This function is used for else part of let block similar to if-else statement
 * example usage: ===>
 * var info = someObj.getInfo()
 * info?.let {
 *  // Your OP here
 * }.orElse {
 *  // Your else part OP here
 * }
 * */
inline fun <R> R?.orElse(block: () -> R): R {
    return this ?: block()
}
