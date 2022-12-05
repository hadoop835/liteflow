package com.yomahub.liteflow.script.python;

import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.CreditCodeUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.yomahub.liteflow.script.jsr223.JSR223ScriptExecutor;
import org.python.antlr.PythonParser;
import org.python.util.CodegenUtils;
import org.python.util.JycompileAntTask;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Python脚本语言的执行器实现
 * @author Bryan.Zhang
 * @since 2.9.5
 */
public class PythonScriptExecutor extends JSR223ScriptExecutor {

    @Override
    protected String scriptEngineName() {
        return "python";
    }

    @Override
    protected String convertScript(String script) {
        String[] lineArray = script.split("\\n");
        List<String> noBlankLineList = Arrays.stream(lineArray).filter(
                s -> !StrUtil.isBlank(s)
        ).collect(Collectors.toList());

        //用第一行的缩进的空格数作为整个代码的缩进量
        String blankStr = ReUtil.getGroup0("^[ ]*", noBlankLineList.get(0));

        //重新构建python脚本
        StringBuilder scriptSB = new StringBuilder();
        noBlankLineList.forEach(s
                -> scriptSB.append(StrUtil.format("{}\n", s.replaceFirst(blankStr, StrUtil.EMPTY))));

        return scriptSB.toString();
    }
}
