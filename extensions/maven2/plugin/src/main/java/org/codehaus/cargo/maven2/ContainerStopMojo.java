/*
 * ========================================================================
 *
 * Codehaus CARGO, copyright 2004-2011 Vincent Massol, 2012-2016 Ali Tokmen.
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
package org.codehaus.cargo.maven2;

import org.apache.maven.plugin.MojoExecutionException;
import org.codehaus.cargo.container.Container;
import org.codehaus.cargo.container.LocalContainer;

/**
 * Stop a running container using Cargo.
 * 
 * @goal stop
 * @requiresDependencyResolution test
 */
public class ContainerStopMojo extends AbstractCargoMojo
{
    /**
     * {@inheritDoc}
     */
    @Override
    public void doExecute() throws MojoExecutionException
    {
        Container container = createContainer();

        if (!container.getType().isLocal())
        {
            throw new MojoExecutionException("Only local containers can be stopped");
        }

        ((LocalContainer) container).stop();
        waitDeployableMonitor(container, false);
    }
}
