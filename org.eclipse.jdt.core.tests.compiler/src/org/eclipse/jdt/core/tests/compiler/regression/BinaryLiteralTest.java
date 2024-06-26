/*******************************************************************************
 * Copyright (c) 2011, 2014 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jdt.core.tests.compiler.regression;

import java.util.Map;

import org.eclipse.jdt.internal.compiler.impl.CompilerOptions;

import junit.framework.Test;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class BinaryLiteralTest extends AbstractRegressionTest {
	public BinaryLiteralTest(String name) {
		super(name);
	}
	public static Test suite() {
		return buildMinimalComplianceTestSuite(testClass(), F_1_7);
	}

	public static Class testClass() {
		return BinaryLiteralTest.class;
	}

	public void test001() {
		this.runConformTest(
			new String[] {
				"X.java",
				"""
					public class X {
						public static void main(String[] args) {
							System.out.println(0b001);
						}
					}"""
			},
			"1");
	}
	public void test002() {
		this.runNegativeTest(
			new String[] {
				"X.java",
				"""
					public class X {
						public static void main(String[] args) {
							System.out.println(0b);
						}
					}"""
			},
			"""
				----------
				1. ERROR in X.java (at line 3)
					System.out.println(0b);
					                   ^^
				Invalid binary literal number (only '0' and '1' are expected)
				----------
				""");
	}
	public void test003() {
		this.runNegativeTest(
			new String[] {
				"X.java",
				"""
					public class X {
						public static void main(String[] args) {
							System.out.println(0b2);
						}
					}"""
			},
			"""
				----------
				1. ERROR in X.java (at line 3)
					System.out.println(0b2);
					                   ^^
				Invalid binary literal number (only '0' and '1' are expected)
				----------
				""");
	}
	public void test004() {
		Map customedOptions = getCompilerOptions();
		customedOptions.put(CompilerOptions.OPTION_Compliance, CompilerOptions.VERSION_1_6);
		customedOptions.put(CompilerOptions.OPTION_Source, CompilerOptions.VERSION_1_6);
		customedOptions.put(CompilerOptions.OPTION_TargetPlatform, CompilerOptions.VERSION_1_6);
		this.runNegativeTest(
			new String[] {
				"X.java",
				"""
					public class X {\t
						public static void main(String[] args) {
							System.out.println(0b1110000);
						}
					}"""
			},
			"""
				----------
				1. ERROR in X.java (at line 3)
					System.out.println(0b1110000);
					                   ^^^^^^^^^
				Binary literals can only be used with source level 1.7 or greater
				----------
				""",
			null,
			true,
			customedOptions);
	}
	public void test005() {
		Map customedOptions = getCompilerOptions();
		customedOptions.put(CompilerOptions.OPTION_Compliance, CompilerOptions.VERSION_1_6);
		customedOptions.put(CompilerOptions.OPTION_Source, CompilerOptions.VERSION_1_6);
		customedOptions.put(CompilerOptions.OPTION_TargetPlatform, CompilerOptions.VERSION_1_6);
		this.runNegativeTest(
			new String[] {
				"X.java",
				"""
					public class X {\t
						public static void main(String[] args) {
							System.out.println(-0b1110000);
						}
					}"""
			},
			"""
				----------
				1. ERROR in X.java (at line 3)
					System.out.println(-0b1110000);
					                    ^^^^^^^^^
				Binary literals can only be used with source level 1.7 or greater
				----------
				""",
			null,
			true,
			customedOptions);
	}
	public void test006() {
		Map customedOptions = getCompilerOptions();
		customedOptions.put(CompilerOptions.OPTION_Compliance, CompilerOptions.VERSION_1_6);
		customedOptions.put(CompilerOptions.OPTION_Source, CompilerOptions.VERSION_1_6);
		customedOptions.put(CompilerOptions.OPTION_TargetPlatform, CompilerOptions.VERSION_1_6);
		this.runNegativeTest(
			new String[] {
				"X.java",
				"""
					public class X {\t
						public static void main(String[] args) {
							System.out.println(0b1113000);
						}
					}"""
			},
			"""
				----------
				1. ERROR in X.java (at line 3)
					System.out.println(0b1113000);
					                   ^^^^^
				Binary literals can only be used with source level 1.7 or greater
				----------
				""",
			null,
			true,
			customedOptions);
	}
}
