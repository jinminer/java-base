package com.jinm.deepinjava.apt.processing;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

import static com.jinm.deepinjava.apt.compiler.CompilerDemo.getClassOutputDirectory;
import com.jinm.deepinjava.apt.compiler.Compiler;

/**
 * 编译 + RepositoryAnnotationProcessor 处理
 * 类比 spring 5 org.springframework.context.index.processor.CandidateComponentsIndexer 实现
 */
@Component
public class RepositoryAnnotationProcessorDemo {

    public static void main(String[] args) throws IOException {
        File sourceDirectory = new File(System.getProperty("user.dir"), "/src/main/java/");
        File targetDirectory = getClassOutputDirectory();
        // 基于 Compiler
        Compiler compiler = new Compiler(sourceDirectory, targetDirectory);
        compiler.setProcessors(new RepositoryAnnotationProcessor());
        compiler.compile(
                "com.jinm.deepinjava.apt.reflection.User",
                "com.jinm.deepinjava.apt.reflection.Repository",
                "com.jinm.deepinjava.apt.reflection.CrudRepository",
                "com.jinm.deepinjava.apt.reflection.UserRepository"
        );
    }
}
