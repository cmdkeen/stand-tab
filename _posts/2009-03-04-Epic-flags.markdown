---
layout: post
title: Epic flags
---

Well that was a bit of an **epic** session, didn't really add anything new instead completely ripped out the old crappy way of handling dynamic fields and re-implemented everything.

Now a relatively simple abstract class provides pretty much everything needed for accessing the data, and it all ties in simply with the TableModels that drive the GUI.

The biggest problem was trying to handle a flag (my term for a basically a field) whose associated object contains more than 1 piece of data. Put simply I want to have a table with: TeamName, Speaker1, Speaker2, Round1, Round2 - where Speaker and Round are flags to a collection of data. After the original horrible hack of a fixed array I moved on a single class with an index field, set to -1 for 'normal' values.

Cue great issues with what constituted the flag for the underlying environment map, Speaker1 and Speaker2 are different flags but access the same data structure. The fix was firstly to create a subclass for flags which had an index value, leaving standard flags to just compare their names. This should have worked really well, but for some reason didn't. Well over an hour later (**massive grumble**) I realised I'd not re-implemented getIndex() on the multiple flag, leaving the parent method working away just returning -1. Once fixed and the swearing subsided things worked much better. 

Though the next problem was with the index numbers, and their effect with Maps objects as opposed to more normal Collection objects. A quick instanceof branch later and that was fixed. 

Then a real brainwave hit, just store the fields in the environment by the name in the Flag. When it is retrieved from the store by the wrapping method it can be handled according to whether it is a multiple flag field. Thanks to the massive rewrite this was ridiculously easy, probably *too* easy, but it seems to work.

All in all pretty good, but how on earth did it take ~ 8 hours!