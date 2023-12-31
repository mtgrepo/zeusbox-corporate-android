/*
 * Nextcloud Android client application
 *
 *  @author Álvaro Brey
 *  Copyright (C) 2023 Álvaro Brey
 *  Copyright (C) 2023 Nextcloud GmbH
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU AFFERO GENERAL PUBLIC LICENSE
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU AFFERO GENERAL PUBLIC LICENSE for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.zeuscloud.test

import android.app.Instrumentation
import androidx.test.platform.app.InstrumentationRegistry
import dagger.android.AndroidInjector
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class InjectionOverrideRule(private val overrideInjectors: Map<Class<*>, AndroidInjector<*>>) : TestRule {
    override fun apply(base: Statement, description: Description): Statement = object : Statement() {
        override fun evaluate() {
            val instrumentation: Instrumentation = InstrumentationRegistry.getInstrumentation()
            val testApp = instrumentation.targetContext.applicationContext as TestMainApp
            overrideInjectors.entries.forEach {
                testApp.addTestInjector(it.key, it.value)
            }
            base.evaluate()
            testApp.clearTestInjectors()
        }
    }
}
