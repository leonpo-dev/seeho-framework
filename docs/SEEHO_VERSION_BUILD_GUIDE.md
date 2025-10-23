# 🧩 Seeho System Versioning & Build Standards
# 🧩 Seeho系统版本与构建规范


---

## 1. Overview / 概述

This document defines the internal versioning rules, build lifecycle, and deployment process for the **Seeho System**.  
It ensures consistent dependency management, semantic versioning, and smooth environment promotion from **UAT → Release → Production**.

本文档定义了 **Seeho系统** 的内部版本规则、构建生命周期及环境流转流程，确保依赖管理一致、语义化版本规范、以及 **UAT → Release → 生产环境** 的顺畅衔接。


---

## 2. Version Numbering / 版本号规范

### Format / 格式  

| Type | Description / 说明 | Example |
|------|------------------|----------|
| `major` | Major changes or incompatible API updates / 重大变更或不兼容更新 | 2.0.0 |
| `minor` | Feature additions, backward compatible / 新功能增加且向后兼容 | 2.1.0 |
| `patch` | Bug fixes or performance tuning / 修复或性能优化 | 2.1.3 |
| `SNAPSHOT` | Development snapshot before release / 开发阶段快照版 | 2.1.3-SNAPSHOT |


---

## 3. Module Dependency Management / 模块依赖管理

All child modules must inherit from the **Seeho Parent POM**.  
所有子模块必须继承统一的 **Seeho父级POM**。

- Version alignment managed under `<dependencyManagement>`
- Strict dependency locking — no arbitrary upgrades
- Internal libraries (e.g., `seeho-craft`, `seeho-common`) must be versioned and published to the internal Maven repo

示例：
```xml
<parent>
    <groupId>com.seeho</groupId>
    <artifactId>seeho-parent</artifactId>
    <version>1.2.0</version>
</parent>
4. Build Lifecycle / 构建生命周期
Stage	Version Type	Branch	Description
Development	x.x.x-SNAPSHOT	develop	Continuous integration builds
UAT Test	x.x.x-rc (Release Candidate)	release/x.x.x	Deployed to UAT for verification
Production	x.x.x	main	Stable release deployed to production

构建阶段说明：

开发分支使用 SNAPSHOT 版本；

进入UAT前打上 -rc 版本标签；

验收后合并到 main 并发布正式版本。

5. Jenkins Build Parameters / Jenkins构建参数
Parameter	Description / 说明	Example
BRANCH_NAME	Git branch to build	develop
BUILD_VERSION	Version tag	1.2.0-SNAPSHOT
CLEAN_CACHE	Whether to clean Maven cache	true/false
ENV	Deployment target	dev / uat / release / prod

Cache Strategy / 缓存策略：

开发分支启用缓存以加快构建；

UAT/Release阶段自动清理缓存，确保拉取最新依赖。

6. Artifact Publishing / 构建产物发布

构建产物（JAR / Docker Image）命名规范：

seeho-<module>-<version>.jar
seeho-<module>:<version> (Docker tag)


发布流程：

Jenkins 打包并上传到私有Maven仓库或Docker Registry

生成对应 tag 与 changelog

通知测试或运维团队部署到目标环境

7. Environment Promotion / 环境流转流程
Develop → UAT → Release → Production


Each promotion requires:

✅ All unit/integration tests passed

✅ Jenkins build successful

✅ Artifact tagged & signed

✅ Manual review approval

每次环境流转必须满足：

✅ 单元/集成测试全部通过

✅ Jenkins构建成功

✅ 产物打包签名

✅ 经负责人审核批准

8. Semantic Versioning Commit Rules / 语义化提交规范

遵循 Conventional Commits
：

feat: add user login service
fix: resolve timeout issue
chore: update build script
docs: update module readme


自动生成 changelog 时可根据 commit 类型分类统计。

9. Version Rollback / 版本回退策略

所有发布版本必须打 Git Tag（如 v1.2.0）；

回退版本仅允许回滚到上一个 Release；

Rollback 操作必须在独立分支中进行并验证。

10. Summary / 总结

This guide ensures Seeho system maintains:

Consistent version control

Reproducible builds

Transparent deployment traceability

本规范确保 Seeho 系统实现：

一致的版本控制

可复现的构建流程

透明的部署可追溯性