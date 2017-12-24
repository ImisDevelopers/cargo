/*
 * ========================================================================
 *
 * Codehaus CARGO, copyright 2004-2011 Vincent Massol, 2012-2018 Ali Tokmen.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */
package org.codehaus.cargo.container.jboss.internal;

import org.codehaus.cargo.container.property.RemotePropertySet;
import org.codehaus.cargo.container.spi.configuration.AbstractRuntimeConfigurationCapability;

/**
 * Capabilities of JBoss's runtime configuration.
 */
public abstract class AbstractJBossRuntimeConfigurationCapability extends
    AbstractRuntimeConfigurationCapability
{
    /**
     * Initialize the configuration-specific supports Map.
     */
    public AbstractJBossRuntimeConfigurationCapability()
    {
        this.propertySupportMap.put(RemotePropertySet.URI, Boolean.FALSE);
    }
}
