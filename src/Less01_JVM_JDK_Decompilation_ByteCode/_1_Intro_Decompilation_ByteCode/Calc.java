package Less01_JVM_JDK_Decompilation_ByteCode._1_Intro_Decompilation_ByteCode;

public class Calc {
	public int Sum(int a){
		int b = 1;
		return a + b;
	}
}
//Результаты декомпиляции разными декомпиляторами:
//с сайта http://www.javadecompilers.com/
//
// Decompiled by Procyon v0.5.36
//
/**********************
public class Calc
{
	public int Sum(final int n) {
		return n + 1;
	}
}
**********************/
//
// Decompiled with CFR 0.150.
//
/*********************
public class Calc {
	public int Sum(int n) {
		int n2 = 1;
		return n + n2;
	}
}
***********************/
//
// Decompiled JDCore
//
/**********************
public class Calc {
	public Calc() {}
	
	public int Sum(int paramInt) {
		int i = 1;
		return paramInt + i;
	}
}
***********************/
//
// Decompiled jadx with Android support
//
/**********************
Job Outputcode: -1
Job Output:
INFO - loading ...
ERROR - jadx error: Error load file: Calc.class
jadx.core.utils.exceptions.JadxRuntimeException: Error load file: Calc.class
at jadx.api.JadxDecompiler.loadFiles(JadxDecompiler.java:121)
at jadx.api.JadxDecompiler.load(JadxDecompiler.java:88)
at jadx.cli.JadxCLI.processAndSave(JadxCLI.java:34)
at jadx.cli.JadxCLI.main(JadxCLI.java:19)
Caused by: java.lang.IllegalArgumentException: Unsupported class file major version 59
at org.objectweb.asm.ClassReader.(ClassReader.java:195)
at org.objectweb.asm.ClassReader.(ClassReader.java:176)
at org.objectweb.asm.ClassReader.(ClassReader.java:162)
at org.objectweb.asm.ClassReader.(ClassReader.java:283)
at jadx.core.utils.AsmUtils.getNameFromClassFile(AsmUtils.java:17)
at jadx.core.utils.files.InputFile.loadFromClassFile(InputFile.java:211)
at jadx.core.utils.files.InputFile.searchDexFiles(InputFile.java:65)
at jadx.core.utils.files.InputFile.addFilesFrom(InputFile.java:40)
at jadx.api.JadxDecompiler.loadFiles(JadxDecompiler.java:119)
... 3 common frames omitted
*************************/

//
// Decompiled Fernflower
//
/***********************
public class Calc {
	
	public int Sum(int var1) {
		byte var2 = 1;
		return var1 + var2;
	}
}
***********************/

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3)
// Source File Name:   Calc.java

/*********************
public class Calc
{
	
	public Calc()
	{
	}
	
	public int Sum(int i)
	{
		int j = 1;
		return i + j;
	}
}
**********************/