# Create a comprehensive structure for Java interview questions covering all versions and levels
java_interview_structure = {
    "java_versions": {
        "Java 1.0 (1996)": [
            "Basic language features",
            "Object-oriented programming",
            "Basic data types and operators"
        ],
        "Java 1.1 (1997)": [
            "Inner classes",
            "JavaBeans",
            "JDBC",
            "RMI (Remote Method Invocation)",
            "Reflection"
        ],
        "Java 1.2 (1998) - J2SE": [
            "Collections Framework",
            "Swing GUI",
            "JIT compiler",
            "Policy-based security"
        ],
        "Java 1.3 (2000)": [
            "HotSpot JVM",
            "JNDI",
            "JavaSound",
            "Dynamic proxies"
        ],
        "Java 1.4 (2002)": [
            "Assert keyword",
            "Regular expressions",
            "NIO (New I/O)",
            "Logging API",
            "XML processing"
        ],
        "Java 5 (2004) - J2SE 5.0": [
            "Generics",
            "Annotations",
            "Autoboxing/Unboxing",
            "Enhanced for loop",
            "Enums",
            "Varargs",
            "Static imports"
        ],
        "Java 6 (2006) - Java SE 6": [
            "Scripting support",
            "Compiler API",
            "Web services",
            "JAXB 2.0"
        ],
        "Java 7 (2011)": [
            "Try-with-resources",
            "Multi-catch exception handling",
            "Diamond operator",
            "String in switch",
            "Binary literals",
            "Underscore in numeric literals",
            "Fork/Join framework"
        ],
        "Java 8 (2014) - LTS": [
            "Lambda expressions",
            "Stream API",
            "Optional class",
            "Default methods in interfaces",
            "Method references",
            "Date/Time API",
            "CompletableFuture",
            "Parallel streams"
        ],
        "Java 9 (2017)": [
            "Module System (Project Jigsaw)",
            "JShell",
            "Process API improvements",
            "Collection factory methods",
            "Stream API enhancements",
            "Private methods in interfaces"
        ],
        "Java 10 (2018)": [
            "Local variable type inference (var)",
            "Garbage collector improvements",
            "Application class-data sharing"
        ],
        "Java 11 (2018) - LTS": [
            "String methods (strip, repeat, isBlank)",
            "Files methods",
            "Collection.toArray() overload",
            "HTTP Client API",
            "Local variable syntax for lambda"
        ],
        "Java 12 (2019)": [
            "Switch expressions (preview)",
            "JVM constants API",
            "Shenandoah garbage collector"
        ],
        "Java 13 (2019)": [
            "Text blocks (preview)",
            "Switch expressions enhancements"
        ],
        "Java 14 (2020)": [
            "Switch expressions (standard)",
            "Records (preview)",
            "Pattern matching for instanceof (preview)",
            "Helpful NullPointerExceptions"
        ],
        "Java 15 (2020)": [
            "Text blocks (standard)",
            "Sealed classes (preview)",
            "Hidden classes"
        ],
        "Java 16 (2021)": [
            "Records (standard)",
            "Pattern matching for instanceof (standard)",
            "Sealed classes (second preview)",
            "Vector API (incubator)"
        ],
        "Java 17 (2021) - LTS": [
            "Sealed classes (standard)",
            "Pattern matching for switch (preview)",
            "Strong encapsulation for JDK internals",
            "Context-specific deserialization filters"
        ],
        "Java 18 (2022)": [
            "UTF-8 by default",
            "Simple web server",
            "Code snippets in documentation",
            "Pattern matching for switch (second preview)"
        ],
        "Java 19 (2022)": [
            "Virtual threads (preview)",
            "Structured concurrency (incubator)",
            "Pattern matching for switch (third preview)",
            "Record patterns (preview)"
        ],
        "Java 20 (2023)": [
            "Scoped values (incubator)",
            "Record patterns (second preview)",
            "Pattern matching for switch (fourth preview)",
            "Virtual threads (second preview)"
        ],
        "Java 21 (2023) - LTS": [
            "Virtual threads (standard)",
            "Pattern matching for switch (standard)",
            "Record patterns (standard)",
            "String templates (preview)",
            "Sequenced collections"
        ],
        "Java 22 (2024)": [
            "Unnamed variables and patterns",
            "String templates (second preview)",
            "Statements before super()",
            "Multi-source-file programs"
        ],
        "Java 23 (2024)": [
            "Primitive types in patterns (preview)",
            "Module import declarations (preview)",
            "Markdown documentation comments (preview)"
        ],
        "Java 24 (2025) - Expected": [
            "To be announced - likely continuation of preview features",
            "Performance improvements",
            "Security enhancements"
        ],
        "Java 25 (2025) - Expected LTS": [
            "Major LTS release with stabilized features",
            "Enhanced performance optimizations",
            "New language features from previews"
        ]
    }
}

print("Java Versions and Major Features Overview:")
print("=" * 60)

for version, features in java_interview_structure["java_versions"].items():
    print(f"\n{version}:")
    for feature in features:
        print(f"  • {feature}")

print(f"\nTotal Java versions covered: {len(java_interview_structure['java_versions'])}")