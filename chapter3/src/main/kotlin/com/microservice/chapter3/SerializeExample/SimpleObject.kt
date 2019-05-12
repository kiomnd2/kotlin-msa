package com.microservice.chapter3.SerializeExample

class SimpleObject {
    public var name ="hello"
    private var place ="world"
    public fun getPalce() = place // public 을 찾아 직렬화를 시도함
}