#leetcode模板配置

## TempFilePath
D:\javacode\workspace_idea\source\LeetCode\src

## CodeFileName
P$!{question.frontendQuestionId}$!velocityTool.camelCaseName(${question.titleSlug})


## CodeTemplate
${question.content}

package leetcode.editor.cn;

//Java：${question.title}

public class P$!{question.frontendQuestionId}$!velocityTool.camelCaseName(${question.titleSlug}) {
    public static void main(String[] args) {
        Solution solution = new P${question.frontendQuestionId}$!velocityTool.camelCaseName(${question.titleSlug})().new Solution();
        // 测试代码
    }
    //力扣代码
    ${question.code}
}