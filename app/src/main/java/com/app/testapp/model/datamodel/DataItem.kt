package com.app.testapp.model.datamodel

data class DataItem(var name: String, var icon: Int, var isSelected:Boolean,var childList :ArrayList<ChildData>)