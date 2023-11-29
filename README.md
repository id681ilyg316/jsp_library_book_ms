## 本项目实现的最终作用是基于JSP图书馆图书管理系统
## 分为3个角色
### 第1个角色为管理员角色，实现了如下功能：
 - 图书管理
 - 用户管理
 - 管理员登录
 - 罚款缴纳
### 第2个角色为游客角色，实现了如下功能：
 - 查看图书详情
 - 查看所有图书
### 第3个角色为学生角色，实现了如下功能：
 - 借阅记录查看
 - 图书借阅
 - 图书归还
 - 学生登录
 - 密码修改
## 数据库设计如下：
# 数据库设计文档

**数据库名：** library_book_ms

**文档版本：** 


| 表名                  | 说明       |
| :---: | :---: |
| [book_info](#book_info) |  |
| [borrow_info](#borrow_info) |  |
| [sys_info](#sys_info) |  |
| [user_info](#user_info) |  |

**表名：** <a id="book_info">book_info</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | bookid |   bigint   | 20 |   0    |    N     |  Y   |       |   |
|  2   | bookname |   varchar   | 255 |   0    |    N     |  N   |       |   |
|  3   | type |   varchar   | 255 |   0    |    N     |  N   |       | 类型  |
|  4   | isbn |   varchar   | 255 |   0    |    N     |  N   |       | ISBN号  |
|  5   | author |   varchar   | 255 |   0    |    N     |  N   |       | 作者  |
|  6   | press |   varchar   | 255 |   0    |    N     |  N   |       |   |
|  7   | pubtime |   datetime   | 19 |   0    |    N     |  N   |       |   |
|  8   | allquantity |   int   | 10 |   0    |    N     |  N   |       |   |
|  9   | aviquantity |   int   | 10 |   0    |    N     |  N   |       |   |
|  10   | imgpath |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  11   | pdfPath |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  12   | location |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="borrow_info">borrow_info</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   bigint   | 20 |   0    |    N     |  Y   |       |   |
|  2   | userid |   varchar   | 255 |   0    |    N     |  Y   |       | 用户ID  |
|  3   | bookid |   int   | 10 |   0    |    N     |  Y   |       |   |
|  4   | bortime |   datetime   | 19 |   0    |    N     |  N   |       |   |
|  5   | rettime |   datetime   | 19 |   0    |    N     |  N   |       |   |
|  6   | reltime |   datetime   | 19 |   0    |    Y     |  N   |   NULL    |   |
|  7   | overfine |   float   | 7 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="sys_info">sys_info</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | ID  |
|  2   | name |   varchar   | 255 |   0    |    N     |  N   |       | 名字  |
|  3   | password |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 密码  |

**表名：** <a id="user_info">user_info</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | ID |   bigint   | 20 |   0    |    N     |  Y   |       | 主键  |
|  2   | name |   varchar   | 12 |   0    |    N     |  N   |       | 名字  |
|  3   | college |   varchar   | 255 |   0    |    N     |  N   |       |   |
|  4   | major |   varchar   | 255 |   0    |    N     |  N   |       |   |
|  5   | classes |   varchar   | 255 |   0    |    N     |  N   |       |   |
|  6   | imgpath |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  7   | password |   varchar   | 255 |   0    |    N     |  N   |       | 密码  |

**运行不出来可以微信 javape 我的公众号：源码码头**
