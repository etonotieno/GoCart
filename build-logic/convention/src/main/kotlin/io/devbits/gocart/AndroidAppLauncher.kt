/*
 * Copyright 2023 Eton Otieno
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
package io.devbits.gocart

import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import java.util.Locale
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureLauncherTasks() {
    androidComponents {
        onVariants { variant ->
            tasks.register("open${variant.name.capitalizeString()}") {
                dependsOn(tasks.named("install${variant.name.capitalizeString()}"))

                doLast {
                    exec {
                        commandLine = "adb shell monkey -p ${variant.applicationId.get()} -c android.intent.category.LAUNCHER 1".split(" ")
                    }
                }
            }
        }
    }
}

private fun String.capitalizeString(): String = this.replaceFirstChar {
    if (it.isLowerCase()) {
        it.titlecase(
            Locale.getDefault(),
        )
    } else {
        it.toString()
    }
}

private fun Project.androidComponents(action: ApplicationAndroidComponentsExtension.() -> Unit) =
    extensions.configure<ApplicationAndroidComponentsExtension>(action)
