/*******************************************************************************
 * Copyright (c) 2008, 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.acceleo.examples.internal.wizard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.acceleo.examples.internal.AcceleoExamplesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * This will allow the user to unzip the ecore2unittests example module.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class Ecore2UnitTestsExampleWizard extends AbstractExampleWizard {
	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.acceleo.examples.internal.wizard.AbstractExampleWizard#getProjectDescriptors()
	 */
	@Override
	protected Collection<ProjectDescriptor> getProjectDescriptors() {
		final List<ProjectDescriptor> projects = new ArrayList<ProjectDescriptor>(2);
		projects.add(new ProjectDescriptor("org.eclipse.acceleo.examples", //$NON-NLS-1$
				"examples/org.eclipse.acceleo.module.example.ecore2unittests.zip", //$NON-NLS-1$
				"org.eclipse.acceleo.module.example.ecore2unittests")); //$NON-NLS-1$
		return projects;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.acceleo.examples.internal.wizard.AbstractExampleWizard#log(java.lang.Exception)
	 */
	@Override
	protected void log(Exception e) {
		if (e instanceof CoreException) {
			AcceleoExamplesPlugin.getDefault().getLog().log(((CoreException)e).getStatus());
		} else {
			AcceleoExamplesPlugin.getDefault().getLog().log(
					new Status(IStatus.ERROR, AcceleoExamplesPlugin.PLUGIN_ID, IStatus.ERROR, e.getMessage(),
							e));
		}
	}
}
