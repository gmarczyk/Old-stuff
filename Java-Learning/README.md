# Java-Learning

<b> IMPORTANT: </b>as I used some additional TABs the formatting gone wrong on github. I already have changed my TABs into few spaces in settings of IDE to prevent this in future. 

EDIT 03.2017: By looking at the code now, I can tell that my skills are much higher and I can work with java. Also my formatting skills are much higher, as I read a book about it. (Clean code, Robert C. Martin).
<b> Not every attempt was commented below, those are just my own conclusions in early stadium of learning </b>

=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

<b> Basics - First attempt: </b> 

<i>This program actually does nothing and is incomplete, just testing MVC a bit and how java works!</i>

I worked a bit on: try catches, own exceptions, used bridge-like pattern (a bit simplified) with interface instead of abstract class. Everything was suppossed to be compliant with <b>MVC</b> pattern. My thoughts are that if you are really restrictive about this pattern it leads to wasting some processor time because of so much jumps by functions and procedures in big projects with quite a lot of calculations. With a permission to put some things into view or model section this would be more optimal, taking into count fact that you need to be aware of not letting anything go bad by the code itself or anyone who would develop it further. 
Also, I made a .bat files to compile a program and run it from command line.
 
<b>ALSO: </b> when talking about view section. Where to put messages when i have a bridge with reference to interface that indicates which language to use? MODEL section consists the strings with messages? Do i pass them to CONTROLLER by switch case(PL/ENG/UK/Else..), or maybe make an array [0] = pl, [1] = uk etc...? or maybe should i make second bridge with implementer? Then i have to pass the message to CONTROLLER, from it to the VIEW and then use the proper bridge. Additionally if language is specified in data i have to pass this value and decode it to implementer to know from which language i have to get the message. This is messy !!!.
Bridge seems really bad for MVC in my point of view right now. Enum would be 100000000% better. I will try it in the next version of this program to work with my multiple languages here. 

=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

<b> Basics - Second attempt: </b>

This is second version of the first one. I'm not into MVC anymore there, by the way I think I misunderstood what is MODEL and how should it really look. In previous program I treated every class as a single model, what i had to do was do a package "model" and all classes would form my final model. Actually it's hard to find one implementation of MVC pattern on the internet so I'm not sure if I'm doing it well. Nvm...

Anyway, my java teacher said i should do as follows - package "model" that may consist of several classes, but thats my model of app.
Then the view, that can partly be CONTROLLER and I can use it to validate input data n stuff, it is much more handy. Then I dont need VIEW for each invidual class in model, I can make one - so I did. The problem with the bridge from first program is gone now. At least mostly. Maybe the bridge is not the best and there is a better solution but still i wanted to use it instead of using switch case in message procedures in VIEW.

This program consists of my own aritmetic. I know i could use BigInteger, but i wanted to try implementing this by myself. It supports unsigned strings and works properly, maybe not optimally but still works.

<b> Do i need to check for exceptions and throw them in every proc/func if I validate the input data that I pass to my proc/funcs? maybe if Im writing it only for this model and program I can leave it by, but if I write code for someone or a LIB, then I would check it everywhere proc/func can fail. </b>

<b> After update 1: </b>

Just making the code clear, started with javadoc, changed some methods but the functionallity is the same.

<b>???: </b>Still not sure about my knowledgle about throwing exceptions. Is passing the exception to proc/func above right? While my model have a lot of private methods that may throw something, using try catches on so many procedures seems waste of processor time. The second thing is that my app presupposses that data in the model is correct, so methods in the model shouldn't fail. Even if I validate the data input and throw exceptions in just few required procedures, still I had to do something like:

switch - case, default: throw exception - because what else I can do if I think that default cannot happen? Same with some If/ElseIF/Elses like i==1 i==0 i==-1, and what in "else" when my condition acquires value from func that can return only -1,0,1? Else it throws?

Maybe there are better options about the code to pass such an events by. Is my code not good enought? Is there something I am doing wrong?

Anyway, got the .jar with a .bat that should run it. Added a lot of comments and generated javadoc. I'm done with this app for now, probably will use it to learn some @Tests and @Annotations or to change the view to normal window GUI.

<b> After update 2: </b>

Code is quite good for now. Application can be developed further, changed some classes to make the program more expansible. Also, included testing libraries and done several tests for each public class that is used, can throw something or go wrong somehow. Divided tests to three types: <b>Invalid, Valid and Boundary cases</b>. For now I think my knowledge about <b>throwing</b> unchecked, checked exceptions is fine, same for <b>testing</b> application, using interface - thats something new comparing to c++. Got basic knowledge about <b>annotations and javadoc</b>.
Also what I changed - I made all private methods not throwing anything, cases when something goes wrong can appear - I assumed that it's programmist duty to use this methods in such a way that those methods won't fail somehow.
<b>Next step:</b> Provide normal GUI instead of console.

=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
