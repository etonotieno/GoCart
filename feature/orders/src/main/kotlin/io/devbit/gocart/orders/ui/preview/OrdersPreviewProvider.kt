/*
 * Copyright 2024 Eton Otieno
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
package io.devbit.gocart.orders.ui.preview

import io.devbit.gocart.orders.ui.OrderStatus
import io.devbit.gocart.orders.ui.UiOrderItem

val sampleRecentOrders = listOf(
    UiOrderItem(
        id = "1",
        number = "12309124",
        price = "284.00",
        date = "August 30th, 2022, 9:00AM",
        status = OrderStatus.Pending,
    ),
    UiOrderItem(
        id = "2",
        number = "12410344",
        price = "284.00",
        date = "August 21st, 2022, 4:25PM",
        status = OrderStatus.Delivered,
    ),
    UiOrderItem(
        id = "3",
        number = "18301930",
        price = "1704.00",
        date = "August 3rd, 2022, 9:00PM",
        status = OrderStatus.ReadyToPickup,
    ),
    UiOrderItem(
        id = "4",
        number = "23100144",
        price = "1365.00",
        date = "October 22nd, 2020, 10:00AM",
        status = OrderStatus.Cancelled,
    ),
    UiOrderItem(
        id = "5",
        number = "12410344",
        price = "1930.00",
        date = "May 1st, 2022, 1:05PM",
        status = OrderStatus.Cancelled,
    ),
)
