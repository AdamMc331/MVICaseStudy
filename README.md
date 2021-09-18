## MVI Case Study

This is a sample application to demonstrate the differences between MVVM and MVI architecture. 

The sample is split into three helpful modules.

### Core Module

This module has some code that is shared between both projects - analytics, data layer, and some UI code
You can explore this package if you want, but it's not necessary to understand.

### MVVM and MVI Modules

The app-mvvm and app-mvi modules should contain applications that look and behave the same way. There will
be a screen for reading preferences, and a screen for updating preferences. I suggest opening the ViewModels
from each module side by side, to start seeing the differences. 