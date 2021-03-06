/*
 * Copyright 2017 Yonghoon Kim
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pickth.postpay.manager

import android.content.Context

/**
 * Created by yonghoon on 2018-04-05
 * Blog   : http://blog.pickth.com
 */
object SavingDataManager {

    private const val POST_PAY = "9999"
    private const val SAVING_PERCENTAGE = "9990"
    private const val SAVING_MONEY = "9991"

    fun getSavingPercentage(context: Context) = context.applicationContext
            .getSharedPreferences(POST_PAY, Context.MODE_PRIVATE)
            .getString(SAVING_PERCENTAGE, "0")
            .toInt()

    fun setSavingPercentage(context: Context, value: Int) {
        context.applicationContext
                .getSharedPreferences(POST_PAY, Context.MODE_PRIVATE)
                .edit()
                .putString(SAVING_PERCENTAGE, value.toString())
                .apply()
    }

    fun getSavingMoney(context: Context) = context.applicationContext
            .getSharedPreferences(POST_PAY, Context.MODE_PRIVATE)
            .getString(SAVING_MONEY, "0")
            .toInt()

    fun setSavingMoney(context: Context, value: Int) {
        context.applicationContext
                .getSharedPreferences(POST_PAY, Context.MODE_PRIVATE)
                .edit()
                .putString(SAVING_MONEY, value.toString())
                .apply()
    }
}