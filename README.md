AndroidBook
========
# 产品概况
### 产品背景
  免费的Android技能学习平台，涵盖了各种常用网站及Android大牛分享的优秀资源，适合不同阶段的Android学习人群。
  以纯干货的形式为平台特点，提供了一个迅速提升Android技能、共同分享进步的学习平台。
### 用户
  主要目标用户是需要学习Android前沿技术的在校学生和熟悉Android开发的IT人群。使用群体为移动端Android智能手机用户。
# 产品功能
## 广告页
#### 用户场景
  用户每次启动App时显示。<br/>
  1.用户点击“跳过按钮”，则直接进入下一个界面；否则3秒后自动跳转到下一个界面；<br/>
  2.如果是安装后首次打开App，则下一个界面为引导页；否则下一个界面为首页。<br/>
## 引导页
#### 用户场景
  用户首次打开App，广告页之后显示。<br/>
  1.只显示一次，再次打开App时不再显示引导页。<br/>
  2.引导页共3页，滑到最后一页时显示“欢迎进入”按钮，点击按钮进入首页。<br/>
#### 关键技术
  FrameLayout+ViewPager实现图片轮播<br/>
## 注册
#### 用户场景
  用户进入App后在“我的”界面点击头像，进入登录界面，点击“注册”，进入注册界面。用户需要设置用户名和密码进行注册。<br/>
  1.注册成功后将注册信息传回登录界面，自动执行登录按钮的点击事件，接下来跳转到首页。<br/>
#### 关键技术
  MVP+Retrofit2+MD（TextInputLayout+TextInputEditText）
## 登录
#### 用户场景
  用户进入APP后在我的页面点击头像登录，或在未登录状态下进行需要登录权限的操作时跳转到登录页面。<br/>
  1.若从“我的”界面直接登录，登陆成功后返回“我的”，如果从注册界面注册完后登录，则返回“首页”。<br/>
  2.登录成功后更新用户名信息。<br/>
#### 关键技术
  MVP+Retrofit2+MD+EventBus
  SharedPreference存储用户名和密码
  单例模式
  获取上一个Activity的方法
## 首页列表
#### 用户场景
  用户进入App后在“首页”中显示的文章列表，登录用户和非登录用户都可以浏览文章内容。<br/>
  1.顶部为Banner实现的首页轮播图，点击图片可以查看具体内容；<br/>
  2.列表为系统提供的一些热门文章，点击标题可以查看具体内容。<br/>
#### 关键技术
  MVP+Retrofit2+Banner+RecyclerView+MD(SwipeRefreshLayout、CardView)<br/>
  自定义View,实现上拉加载更多<br/>
  自定义View，实现圆形的图标<br/>
  WebView加载网页<br/>
## 知识体系
#### 用户场景
  点击底部标签栏“知识体系”进入知识体系界面，显示一级标题的列表，点击一级标题，进入该一级标题下的二级标题列表，点击二级标题，
  进入该二级标题下的文章列表，点击可以查看文章内容。登录用户和非登录用户都可以查看。
#### 关键技术
  MVP+Retrofit2+RecyclerView+MD(SwipeRefreshLayout、CardView)
## 项目
#### 用户场景
  点击底部标签栏“项目”进入项目界面，显示项目的分类，点击分类可以查看当前类别里面的项目列表。登录用户和非登陆用户都可以查看。
#### 关键技术
  MVP+Retrofit2+RecyclerView+Glide+MD(SwipeRefreshLayout、CardView)
## 导航
#### 用户场景
  点击顶部标题栏的导航按钮进入导航界面，可以看到一些常用分类，并且以标签的形式列出了分类下的内容，点击小标签可以查看具体内容。
#### 关键技术
  MVP+Retrofit2+RecyclerView+TagFlowLayout+MD(SwipeRefreshLayout、CardView)
## 搜索
#### 用户场景
  点击顶部标题栏的搜索按钮进入搜索界面，可以输入关键词进行搜索。<br/>
  1.搜索后再次进入搜索界面时会显示历史搜索信息，历史搜索的标签可以点击，点击清空历史搜索可以清空；<br/>
  2.搜索界面会显示搜索热词，但是搜索热词的标签没有点击事件；<br/>
  3.输入关键词后可以点击软键盘的右下键按键，也可以点击搜索按钮进行搜索；<br/>
  4.搜索成功后会显示返回的文章列表。<br/>
#### 关键技术
  MVP+Retrofit2+RecyclerView+TagFlowLayout+MD(SwipeRefreshLayout、CardView)<br/>
  SharedPreference存储历史搜索的信息<br/>
# 运行展示图
#### 广告页
  <img src="https://github.com/lcysl/AndroidBook/raw/master/picture/splash.jpg" width="180" height="300"/>
#### 引导页
  <img src="https://github.com/lcysl/AndroidBook/raw/master/picture/guide1.jpg" width="180" height="300"/>
  <img src="https://github.com/lcysl/AndroidBook/raw/master/picture/guide2.jpg" width="180" height="300"/>
  <img src="https://github.com/lcysl/AndroidBook/raw/master/picture/guide3.jpg" width="180" height="300"/>
#### 注册和登录
  <img src="https://github.com/lcysl/AndroidBook/raw/master/picture/mine.jpg" width="180" height="300"/>
  <img src="https://github.com/lcysl/AndroidBook/raw/master/picture/register.jpg" width="180" height="300"/>
  <img src="https://github.com/lcysl/AndroidBook/raw/master/picture/login.jpg" width="180" height="300"/>
#### 首页文章列表
  <img src="https://github.com/lcysl/AndroidBook/raw/master/picture/home.jpg" width="180" height="300"/>
#### 知识体系
  <img src="https://github.com/lcysl/AndroidBook/raw/master/picture/knowledge_system1.jpg" width="180" height="300"/>
  <img src="https://github.com/lcysl/AndroidBook/raw/master/picture/knowledge_system2.jpg" width="180" height="300"/>
#### 项目
  <img src="https://github.com/lcysl/AndroidBook/raw/master/picture/project1.jpg" width="180" height="300"/>
  <img src="https://github.com/lcysl/AndroidBook/raw/master/picture/project2.jpg" width="180" height="300"/>
#### 导航
  <img src="https://github.com/lcysl/AndroidBook/raw/master/picture/navigate.jpg" width="180" height="300"/>
#### 搜索
  <img src="https://github.com/lcysl/AndroidBook/raw/master/picture/search.jpg" width="180" height="300"/>
