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
package io.devbit.gocart.orders.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import io.devbit.gocart.orders.ui.preview.sampleRecentOrders
import io.devbits.gocart.designsystem.component.IconContainer
import io.devbits.gocart.designsystem.component.SecondaryGrayButton
import io.devbits.gocart.designsystem.theme.GoCartTheme
import io.devbits.gocart.resources.R as resourcesR

@Composable
fun OrderItem(
    order: UiOrderItem,
    onViewSummary: (id: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                IconContainer(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                ) {
                    Icon(
                        painter = painterResource(resourcesR.drawable.ic_outlined_order),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp),
                    )
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Order #${order.number}",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface,
                    )

                    Text(
                        text = order.status.text,
                        style = MaterialTheme.typography.bodyMedium,
                        color = order.status.color,
                    )

                    Text(
                        text = order.date,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }

                Text(
                    text = "Ksh ${order.price}",
                    style = MaterialTheme.typography.titleSmall,
                )
            }

            SecondaryGrayButton(
                text = "View order summary",
                onClick = { onViewSummary(order.id) },
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .align(Alignment.CenterHorizontally)
                    .height(32.dp),
            )
        }
    }
}

@PreviewLightDark
@Preview
@Composable
private fun OrderItemPreview() {
    GoCartTheme {
        OrderItem(order = sampleRecentOrders.first(), onViewSummary = {})
    }
}
