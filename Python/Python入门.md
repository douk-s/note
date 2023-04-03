# Python入门

## 第一行代码

```python
print("hello")
```



## 基础语法

### 注释

单行注释

```python
#注解符号
```

多行注释

```python
'''
多行注释
多行注释
'''

"""
123
abc
"""
```

### 行与缩进

python最具特色的就是使用缩进来表示代码块，不需要使用大括号 **{}** 。

缩进的空格数是可变的，但是同一个代码块的语句必须包含相同的缩进空格数。

```python
if 1==1:
    print("123")
else:
        print("abc")
print("abab")#因为缩进不一样所以不在else里
```



### 多行语句

当代码过长时可以使用 反斜杠  \ 来实现多行语句 

```python
a = '123'
b = 'abc'
c = 'efg'
d = a+\
    b+\
    c
```

 在 [], {}, 或 () 中的多行语句，不需要使用反斜杠   \

```python
total = ['item_one', 'item_two', 'item_three',
        'item_four', 'item_five']
```

### 数字(Number)类型

python中数字有四种类型：整数、布尔型、浮点数和复数。

- **int** (整数), 如 1, 只有一种整数类型 int，表示为长整型，没有 python2 中的 Long。
- **bool** (布尔), 如 True。
- **float** (浮点数), 如 1.23、3E-2
- **complex** (复数), 如 1 + 2j、 1.1 + 2.2j

### 字符串(String)

- Python 中单引号 **'** 和双引号 **"** 使用完全相同。
- 使用三引号(**'''** 或 **"""**)可以指定一个多行字符串。
- 转义符 *\\*。
- 反斜杠可以用来转义，使用 **r** 可以让反斜杠不发生转义。 如 **r"this is a line with \n"** 则 **\n** 会显示，并不是换行。
- 按字面意义级联字符串，如 **"this " "is " "string"** 会被自动转换为 **this is string**。
- 字符串可以用 **+** 运算符连接在一起，用 ***** 运算符重复。
- Python 中的字符串有两种索引方式，从左往右以 **0** 开始，从右往左以 **-1** 开始。
- Python 中的字符串不能改变。
- Python 没有单独的字符类型，一个字符就是长度为 1 的字符串。
- 字符串的截取的语法格式如下：**变量[头下标:尾下标:步长]**

```python
str='123456789'
 
print(str)                 # 输出字符串
print(str[0:-1])           # 输出第一个到倒数第二个的所有字符
print(str[0])              # 输出字符串第一个字符
print(str[2:5])            # 输出从第三个开始到第五个的字符
print(str[2:])             # 输出从第三个开始后的所有字符
print(str[1:5:2])          # 输出从第二个开始到第五个且每隔一个的字符（步长为2）
print(str * 2)             # 输出字符串两次
print(str + '你好')         # 连接字符串
 
print('------------------------------')
 
print('hello\nrunoob')      # 使用反斜杠(\)+n转义特殊字符
print(r'hello\nrunoob')     # 在字符串前面添加一个 r，表示原始字符串，不会发生转义
```

### 空行

函数之间或类的方法之间用空行分隔，表示一段新的代码的开始。类和函数入口之间也用一行空行分隔，以突出函数入口的开始。

空行与代码缩进不同，空行并不是 Python 语法的一部分。书写时不插入空行，Python 解释器运行也不会出错。但是空行的作用在于分隔两段不同功能或含义的代码，便于日后代码的维护或重构。

**记住：**空行也是程序代码的一部分。

### 输入

```python
input("请输入：")
```

### 同一行写多条语句

语句之间用分号 ; 分割

```python
a='123';b=321
```

### print

默认换行，如果不换行在后面加end=“”

```python
print("123",end="")
```

### 导入模块

import和from...import

将整个模块(somemodule)导入，格式为： **import somemodule**

从某个模块中导入某个函数,格式为： **from somemodule import somefunction**

从某个模块中导入多个函数,格式为： **from somemodule import firstfunc, secondfunc, thirdfunc**

将某个模块中的全部函数导入，格式为： **from somemodule import \***

### 命令行参数

可以使用-h查看参数



## 基本数据类型

### 变量

 变量不需要声明。每个变量在使用前都必须赋值，变量赋值以后该变量才会被创建 

变量就是变量，它没有类型，我们所说的"类型"是变量所指的内存中对象的类型。

等号（=）用来给变量赋值

```python
counter = 100          # 整型变量
miles   = 1000.0       # 浮点型变量
name    = "runoob"     # 字符串
```

### 多个变量赋值

```python
a=b=c=1

#或者多个对象指定多个变量
a,b,c = 1,2,"123"
```

### 标准数据类型

- Number（数字）
- String（字符串）
- List（列表）
- Tuple（元组）
- Set（集合）
- Dictionary（字典）

Python3 的六个标准数据类型中：

- **不可变数据（3 个）：**Number（数字）、String（字符串）、Tuple（元组）
- **可变数据（3 个）：**List（列表）、Dictionary（字典）、Set（集合）



### Number

支持int(**长整型**)、float(浮点数)、bool(布尔值)、complex（复数）

```python
a,b,c,d = 20,5.5,True,4+3j
```

可以用**isinstance**判断

```python
a=111
isinstance(a,int)
#输出True
```

 内置的 type() 函数可以用来查询变量所指的对象类型 

isinstance 和 type 的区别在于：

- type()不会认为子类是一种父类类型。
- isinstance()会认为子类是一种父类类型。

```python
>>> class A:
...     pass
... 
>>> class B(A):
...     pass
... 
>>> isinstance(A(), A)
True
>>> type(A()) == A 
True
>>> isinstance(B(), A)
True
>>> type(B()) == A
False
```

 **注意：***Python3 中，bool 是 int 的子类，True 和 False 可以和数字相加，* **True==1、False==0** *会返回* **True***，但可以通过* **is** *来判断类型。* 

### del

 可以通过使用del语句删除单个或多个对象 

```python
del a
del b , c

```

### 运算

+

-

*

/   除得到一个浮点数

//  除得到一个整数

%

**  乘方



### String(字符串)

用单引号‘ 多引号“括起来，同时使用\转义

字符串截取格式为

```python
变量[头下标:尾下标]

```

以0开始为正序开始，倒数以-1开始



加号+是字符串连接符号，星号*表示复制当前字符串，与数字结合为复制的次数

```python
str = 'Runoob'

print (str)          # 输出字符串
print (str[0:-1])    # 输出第一个到倒数第二个的所有字符
print (str[0])       # 输出字符串第一个字符
print (str[2:5])     # 输出从第三个开始到第五个的字符
print (str[2:])      # 输出从第三个开始的后的所有字符
print (str * 2)      # 输出字符串两次，也可以写成 print (2 * str)
print (str + "TEST") # 连接字符串

```



\反斜杠为转义字符，如果不想转义可以在字符串前加一个r，表示原始字符串

```python
print(r'abc\ndef')

```

 

### List(列表)

List（列表） 是 Python 中使用最频繁的数据类型。

列表可以完成大多数集合类的数据结构实现。列表中元素的类型可以不相同，它支持数字，字符串甚至可以包含列表（所谓嵌套）。

列表是写在方括号 **[]** 之间、用逗号分隔开的元素列表。

和字符串一样，列表同样可以被索引和截取，列表被截取后返回一个包含所需元素的新列表。

```python
变量[头下标:尾下标]

```

 索引值以 **0** 为开始值，**-1** 为从末尾的开始位置。 



和字符串不一样的是列表中的值是可以修改的

```python
a=[1,2,3,4,5]
a[0]=9
a[1,4]=[12,13,14]
#a的值为[9,12,13,14]
a[1:4]=[]
#a的值为[9]

```



列表list的第三个参数为步长

```python
list=[1,2,3,4,5,6]
list[1:4:2]#值为[2,4]

```



更新列表

用append()方法来添加

```python
a=[1,2,3,4,5]
a.append('abc')
print(a)
#输出为[1, 2, 3, 4, 5, 'abc']

```



删除列表元素

del 语句

```python
a=[1,2,3,4]
del a[2]
#[1,2,4]

```

列表对+和*的操作符与字符串相似

+号用于组合列表

*号用重复列表

| Python 表达式                         | 结果                         | 描述                 |
| :------------------------------------ | :--------------------------- | :------------------- |
| len([1, 2, 3])                        | 3                            | 长度                 |
| [1, 2, 3] + [4, 5, 6]                 | [1, 2, 3, 4, 5, 6]           | 组合                 |
| ['Hi!'] * 4                           | ['Hi!', 'Hi!', 'Hi!', 'Hi!'] | 重复                 |
| 3 in [1, 2, 3]                        | True                         | 元素是否存在于列表中 |
| for x in [1, 2, 3]: print(x, end=" ") | 1 2 3                        | 迭代                 |



嵌套列表

```python
a=[[1,2,3],[2,3,4]]
print(a[0][1])
#打印为2

```



列表比较

operator模块的eq方法

```python
import operator

a=[1,2,3,4]
b=[1,2,3,4]
print(operator.eq(a,b))
#打印为True

```



len(list)元素个数

max(list)最大元素

min(list)最小元素

list(seq)将元组转换为列表

| 序号 | 方法                                                         |
| :--- | :----------------------------------------------------------- |
| 1    | list.append(obj) 在列表末尾添加新的对象                      |
| 2    | list.count(obj) 统计某个元素在列表中出现的次数               |
| 3    | list.extend(seq) 在列表末尾一次性追加另一个序列中的多个值（用新列表扩展原来的列表） |
| 4    | list.index(obj)从列表中找出某个值第一个匹配项的索引位置      |
| 5    | [list.insert(index, obj)] 将对象插入列表                     |
| 6    | list.pop([index=-1\])移除列表中的一个元素（默认最后一个元素），并且返回该元素的值 |
| 7    | list.remove(obj) 移除列表中某个值的第一个匹配项              |
| 8    | list.reverse()反向列表中元素                                 |
| 9    | list.sort( key=None, reverse=False) 对原列表进行排序         |
| 10   | list.clear() 清空列表                                        |
| 11   | list.copy()复制列表                                          |



### 元组

创建空元组

```python
tup1=()

```

元组只有一个元素时要在后面加一个 , 号不然会被当运算符

```python
tup1 = (50)
type(tup1)     # 不加逗号，类型为整型
<class 'int'>

tup1 = (50,)
type(tup1)     # 加上逗号，类型为元组
<class 'tuple'>

```



**元组的值不能被修改**

但是可用+号

```python
a=(1,)
a+=(1,1,1)
print(a)
#(1,1,1,1)

```



**元组的值不能被删除**

但是可用del删除整个元组

```python
a=(1,1,1)
del a

```



### 字典

```python
d = {key1 : value1, key2 : value2, key3 : value3 }
```

键必须是唯一的，但值则不必。

值可以取任何数据类型，但键必须是不可变的，如字符串，数字。

```python
tinydict1 = { 'abc': 456 }
tinydict2 = { 'abc': 123, 98.6: 3
```

**创建空字典**

```python
emptyDict={}
```

 使用内建函数 **dict()** 创建字典： 

```python
emptyDict = dict()
 
# 打印字典
print(emptyDict)
 
# 查看字典的数量
print("Length:",len(emptyDict))
 
# 查看类型
print(type(emptyDict))
```

```
{}
Length: 0
<class 'dict'>
```

访问字典里的值

```python
a={'a':1, 'b':2, 'c':3}
print("a:",a['a'])
```

**修改字典**

```python
 
tinydict = {'Name': 'Runoob', 'Age': 7, 'Class': 'First'}
 
tinydict['Age'] = 8               # 更新 Age
tinydict['School'] = "菜鸟教程"  # 添加信息
 
 
print ("tinydict['Age']: ", tinydict['Age'])
print ("tinydict['School']: ", tinydict['School'])
```

**删除字典元素**

能删单一的元素也能清空字典，清空只需一项操作。

显式删除一个字典用del命令，如下实例：

```python
tinydict = {'Name': 'Runoob', 'Age': 7, 'Class': 'First'}
 
del tinydict['Name'] # 删除键 'Name'
tinydict.clear()     # 清空字典
del tinydict         # 删除字典
 
print ("tinydict['Age']: ", tinydict['Age'])
print ("tinydict['School']: ", tinydict['School'])
```

**字典键的特性**

字典值可以是任何的 python 对象，既可以是标准的对象，也可以是用户定义的，但键不行。

两个重要的点需要记住：



不允许同一个键出现两次。创建时如果同一个键被赋值两次，后一个值会被记住

### 集合

可以使用{}或者set创建集合，但是不能用{}创建空集合

```python
parame = {value01,value02,...}
或者
set(value)
```

**添加**

```python
s.add(x)
```

 将元素 x 添加到集合 s 中，如果元素已存在，则不进行任何操作。 

 

也可以添加元素，且参数可以是列表，元组，字典等 

```python
s.update( x )
```

 x 可以有多个，用逗号分开 



**移除元素**

```python
s.remove(x)
```

 将元素 x 从集合 s 中移除，如果元素不存在，则会发生错误。



 移除集合中的元素，且如果元素不存在，不会发生错误  

```python
s.discard( x )
```



 随机删除集合中的一个元素 

```python
s.pop() 
```



清空集合

```python
s.clear()
```



判断元素是否在集合中

```python
x in s
```



