/*
 * ========================================================================
 *
 * Copyright 2007-2008 OW2. Code from this file
 * was originally imported from the OW2 JOnAS project.
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
package org.codehaus.cargo.container.jonas.internal;

import org.codehaus.cargo.container.deployable.DeployableType;
import org.codehaus.cargo.container.internal.J2EEContainerCapability;

/**
 * Capabilities of the JOnAS 4.x container.
 */
public class Jonas4xContainerCapability extends J2EEContainerCapability
{
    /**
     * Add support for EJB and RAR deployable types. {@inheritDoc}
     */
    @Override
    public boolean supportsDeployableType(DeployableType type)
    {
        return type == DeployableType.EJB || type == DeployableType.RAR
            || super.supportsDeployableType(type);
    }
}
