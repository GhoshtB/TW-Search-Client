package com.hfad.model

/*
{
    "success" : true,
    "data" : [
    {
        "name": "Planets",
        "handle": "@planets",
        "favoriteCount": 310,
        "retweetCount": 110,
        "profileImageUrl": "https://twitter.com/planets/photo",
        "text": "Planet"
    },*/

data class Datas(val success:Boolean,val data:List<Tweets>)

data class Tweets(val name:String,val handle:String,val favoriteCount:Int,val retweetCount:Int,val profileImageUrl:String,val text:String)