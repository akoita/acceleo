/*******************************************************************************
 * Copyright (c) 2008, 2010 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.acceleo.internal.ide.ui.editors.template.actions.refactor.rename;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.acceleo.internal.ide.ui.editors.template.AcceleoEditor;
import org.eclipse.acceleo.internal.ide.ui.editors.template.utils.OpenDeclarationUtils;
import org.eclipse.ocl.ecore.Variable;
import org.eclipse.ocl.ecore.VariableExp;
import org.eclipse.search.ui.text.Match;

/**
 * This class is a data object class used to store a variable and its positions in a mtl file.
 * 
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public class AcceleoPositionedVariable {

	/**
	 * The variable.
	 */
	private Variable fVariable;

	/**
	 * All the matches of the variable.
	 */
	private List<Match> fMatches;

	/**
	 * The position of the definition of the variable.
	 */
	private Match fDefinitionMatch;

	/**
	 * The constructor.
	 * 
	 * @param variable
	 *            The variable.
	 * @param editor
	 *            The editor.
	 */
	public AcceleoPositionedVariable(final Variable variable, final AcceleoEditor editor) {
		this.fVariable = variable;
		this.fMatches = new ArrayList<Match>();
		this.findAllPositionedVariables(editor);
	}

	/**
	 * The constructor.
	 * 
	 * @param variableExp
	 *            The variable exp.
	 * @param editor
	 *            The editor.
	 */
	public AcceleoPositionedVariable(final VariableExp variableExp, final AcceleoEditor editor) {
		this.fVariable = (Variable)variableExp.getReferredVariable();
		this.fMatches = new ArrayList<Match>();
		this.findAllPositionedVariables(editor);
	}

	/**
	 * Returns the variable.
	 * 
	 * @return The variable.
	 */
	public Variable getVariable() {
		return this.fVariable;
	}

	/**
	 * Returns the name of the variable.
	 * 
	 * @return The name of the variable.
	 */
	public String getVariableName() {
		return this.fVariable.getName();
	}

	/**
	 * Sets the list of variables.
	 * 
	 * @param matches
	 *            The list of matches.
	 */
	public void setVariableMatches(final List<Match> matches) {
		this.fMatches = matches;
	}

	/**
	 * Returns the matches of all the occurrences of the variable in the template.
	 * 
	 * @return The matches of all the occurrences of the variable in the template.
	 */
	public List<Match> getVariableMatches() {
		return this.fMatches;
	}

	/**
	 * Sets the match of the variable definition.
	 * 
	 * @param match
	 *            the match of the variable definition.
	 */
	public void setVariableDefinitionMatch(final Match match) {
		this.fDefinitionMatch = match;
	}

	/**
	 * Returns the position of the name in the variable definition.
	 * 
	 * @return the position of the name in the variable definition.
	 */
	public Match getVariableDefinitionMatch() {
		return this.fDefinitionMatch;
	}

	/**
	 * Sets all the matches of the current variable in the editor.
	 * 
	 * @param editor
	 *            The acceleo editor.
	 */
	private void findAllPositionedVariables(final AcceleoEditor editor) {
		List<Match> list = OpenDeclarationUtils.findOccurrences(editor, this.fVariable);
		list = OpenDeclarationUtils.findOccurrencesInTemplate(this.fVariable, list,
				editor.getFile());
		this.setVariableMatches(list);

		for (Iterator<Match> iterator = list.iterator(); iterator.hasNext();) {
			final Match match = (Match)iterator.next();

			if (match.getLength() > this.fVariable.getName().length()) {
				this.setVariableDefinitionMatch(match);
			}
		}
	}
}