package com.branch.dellog

import com.branch.dellog.task.CleanTestTask
import com.branch.dellog.task.CleanTask
import com.branch.dellog.utils.DelLogUtil
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

/**
 * Created by Ryze on 2017-2-5.
 */
class DelLogPlugin implements Plugin<Project> {

    static final String GROUP = 'LintCleaner'
    static final String EXTENSION_NAME = 'lintCleaner'
  @Override
  void apply(Project project) {


    project.extensions.create('dellogExtension', DelLogExtension);

    project.afterEvaluate {
      //在gradle 构建完之后执行
      project.logger.error("dellogExtension : " + project.dellogExtension.sourceDir);
      project.logger.error("dellogExtension : " + project.dellogExtension.mDir);

      def rootDir = project.projectDir.toString().plus(project.dellogExtension.sourceDir);

      project.logger.error(rootDir);

      DelLogUtil.delLog(new File(rootDir));
    }

    project.task('dellog', {
      project.logger.error("dellogExtension : " + project.dellogExtension.sourceDir);
      project.logger.error("dellogExtension : " + project.dellogExtension.mDir);

      def rootDir = project.projectDir.toString().plus(project.dellogExtension.sourceDir);

      project.logger.error(rootDir);









      DelLogUtil.delLog(new File(rootDir));
      System.out.println("========================");
      System.out.println("这是第一个插件!");
      System.out.println("========================");









//      project.afterEvaluate {
//        project.android.lintOptions.xmlOutput = new File(project.buildDir, "lintResult.xml")
//      }
//      project.tasks.create('cleanTest', CleanTestTask)










      // 获取外部参数
      project.extensions.create(EXTENSION_NAME, PluginExtension, project)

      // 创建清理任务
      Task cleanTask = project.tasks.create(CleanTask.NAME, CleanTask)

      // 执行完lint后，再执行
      cleanTask.dependsOn project.tasks.getByName('lint')

    })

  }
}
