package com.lintchecks

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import org.jetbrains.uast.UAnnotated
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UMethod
import java.util.*

class CustomLintDetector : Detector(), SourceCodeScanner {
    override fun getApplicableUastTypes(): List<Class<out UElement>>? = listOf(UMethod::class.java)

    override fun createUastHandler(context: JavaContext): UElementHandler? {
        return object : UElementHandler() {
            override fun visitMethod(node: UMethod) {
                if (!node.isConstructor) {
                    val annotations = context.evaluator.getAllAnnotations(node as UAnnotated, false)
                    context.log(null, "%d annotation(s): %s", annotations.size, annotations.joinToString(", ") { it.qualifiedName ?: "null" })
                }
            }
        }
    }

    companion object {
        val ISSUE = Issue.create(
                id = "CustomIssue",
                briefDescription = "abc",
                explanation = "abc",
                implementation = Implementation(
                        CustomLintDetector::class.java,
                        EnumSet.of(Scope.JAVA_FILE, Scope.TEST_SOURCES)
                )
        )
    }
}
