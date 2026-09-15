# Sample lab

This is `ch03-lab02` rendered as the learner sees it. It is a real lab:
it lives in `labs/chapter03.py`, and every task below is verified by
`tests/test_all_labs.py` on every change.

Read it to judge the format. If something here is wrong, the spec is
wrong, and it is cheaper to fix now than after 87 labs.

---

## LAB ch03-lab02 — Comparing Passwords Safely

*Chapter 3: Know Your Variables*

### What you will learn

How to compare two pieces of text correctly, and why the obvious way is wrong.

### Why it matters

This is a login check. If you compare a typed password to a stored password with ==, your check is not comparing the passwords at all — it is asking a different question, and the answer it gives you depends on where the text came from. Text typed by a user, read from a file, or received over a network behaves differently from text written directly in your code. That means the bug hides during your testing and appears in production.

### Explain

A variable that holds an object does not hold the object. It holds a reference to it — think of it as a remote control pointing at something sitting in memory.

A String is an object. So a String variable holds a remote control, not the letters.

That is why there are two different questions you can ask:

    ==          Are these two remote controls pointing at the
                SAME object?
    .equals()   Do these two objects CONTAIN the same thing?

For a password check you always mean the second one. You do not care where the text is stored. You care whether the letters match.

Here is what makes this genuinely dangerous rather than merely wrong. Java saves memory by reusing identical text that you write directly in your code. Two variables both set to "admin" end up pointing at the same stored object, so == returns true and your broken check appears to work. You test it, it passes, you move on.

Then the password arrives from a Scanner, a file, or a network request. Now it is a new object with the same letters. == returns false, and nobody can log in — or, if you wrote the test the other way round, everybody can.

THE MISTAKE TO EXPECT: writing == because you tested it with text written in your own code and it worked. It was never working. It was agreeing with you by accident.

### Example

The word new forces Java to make a separate object, which is how we can see the difference on purpose:

    String stored = new String("falcon");
    String typed  = "falcon";

    System.out.println(stored == typed);
    System.out.println(stored.equals(typed));

prints:

    false
    true

The letters are identical. Both lines are comparing the same two variables. They disagree because they are asking different questions: == asks "same object?" and gets no, .equals() asks "same letters?" and gets yes.

---

### Task 1 — warm-up

A stored password and a typed password hold the same letters, but are separate objects.

The variables are already declared. Print whether the login should be allowed — that is, whether the two hold the same letters.

Expected output:
true

**Starter code**

```java
public class Main {
    public static void main(String[] args) {
        String savedPassword = new String("hunter2");
        String enteredPassword = "hunter2";

        // print whether the login should be allowed
    }
}
```

**Hints** (revealed one at a time)

1. You want to compare the contents, not the references.
2. System.out.println(savedPassword.equals(enteredPassword));

**After you pass, you are shown:**

> You compared contents, so you got true — which is the answer a login check needs.
> >
> > Had you written savedPassword == enteredPassword, this would have printed false and locked out a user who typed the correct password. The word new is what made that happen: it forced Java to build a second object instead of reusing the first.
> >
> > Real input is always a new object. Every password that ever arrives from a keyboard behaves like this one.

---

### Task 2 — variation

Show the difference yourself, on a username this time.

Declare a String called registered holding a new String with the letters j.smith, and a String called submitted holding j.smith written normally.

Print the == comparison on the first line, then the .equals() comparison on the second.

Expected output:
false
true

**Starter code**

```java
public class Main {
    public static void main(String[] args) {
        // your code here
    }
}
```

**Hints** (revealed one at a time)

1. new String("j.smith") makes a separate object; "j.smith" on its own does not.
2. Two println lines: one with registered == submitted, one with registered.equals(submitted).

**After you pass, you are shown:**

> Two lines, same two variables, opposite answers. Nothing about the letters changed between them.
> >
> > Notice what happens if you remove new and write both as plain text: both lines print true, because Java reuses the one stored copy. That version looks like proof that == works on text. It is the exact false confidence that puts this bug into real systems.

---

### Task 3 — apply

Usernames and passwords are not compared the same way in real systems. Usernames usually ignore capital letters — JSmith and jsmith are the same person. Passwords never do.

A user has registered as jsmith with the password Falcon9. They have typed JSMITH and falcon9.

Print USER: followed by whether the usernames match ignoring capitals, then PASS: followed by whether the passwords match exactly.

Expected output:
USER: true
PASS: false

**Starter code**

```java
public class Main {
    public static void main(String[] args) {
        String registeredUser = "jsmith";
        String registeredPass = "Falcon9";

        String typedUser = "JSMITH";
        String typedPass = "falcon9";

        // your code here
    }
}
```

**Hints** (revealed one at a time)

1. There is a second method alongside .equals() that ignores capital letters: .equalsIgnoreCase()
2. Use .equalsIgnoreCase() for the username and .equals() for the password.

**After you pass, you are shown:**

> Two comparisons, two different rules, and the difference is deliberate.
> >
> > Being relaxed about capitals in a username is a kindness — nobody should be locked out for pressing shift. Being relaxed about capitals in a password destroys a large part of its strength: it removes 26 possible characters from every position, making the password far faster to guess.
> >
> > If you had used equalsIgnoreCase on the password, this would have printed true and let the wrong password through.

---

### Task 4 — combine

Now put the credentials inside an object, the way Chapter 2 showed you.

A class called Account is started for you. Give it two String instance variables: username and password.

In main, create one Account, set its username to admin and its password to Tr0ub4dor, then check a login attempt where someone has typed admin and Tr0ub4dor — and print whether each matched.

Expected output:
USER: true
PASS: true

**Starter code**

```java
class Account {
    // fields go here
}

public class Main {
    public static void main(String[] args) {
        // your code here
    }
}
```

**Hints** (revealed one at a time)

1. Inside class Account, declare the two fields: String username; and String password;
2. Account a = new Account(); then a.username = "admin"; and compare with a.password.equals(typedPass)

**After you pass, you are shown:**

> The credentials now live in an object, which is where they belong — one Account is one set of credentials, and you could make a hundred of them.
> >
> > Notice that the == version of this happens to print true as well, because all the text here is written directly in the code. That is precisely the trap. The .equals() version is correct for a reason; the == version is right by luck, and stops being right the moment a real user types something.
> >
> > Chapter 4 fixes the other problem you can see here: account.password is readable by anyone holding the object.

---

### Task 5 — repair

This login check was tested with text written directly in the code, so it passed. Now the password arrives the way a real one does — as a separate object — and it is broken. The correct password is rejected.

Fix it so it prints ACCESS: true, without changing how typedPass is created.

Expected output:
ACCESS: true

**Starter code**

```java
public class Main {
    public static void main(String[] args) {
        String stored = "Zx9!warden";

        // this is how a real password arrives: a new object
        String typedPass = new String("Zx9!warden");

        // this test passed during development. It is wrong.
        System.out.println("ACCESS: " + (stored == typedPass));
    }
}
```

**Hints** (revealed one at a time)

1. The letters are identical. Only the comparison is wrong.
2. Replace stored == typedPass with stored.equals(typedPass).

**After you pass, you are shown:**

> One method call was the entire fix, and the bug was invisible until the data stopped being convenient.
> >
> > This is the shape of the real failure. The developer tests with text typed into the source file, sees true, and ships. The first real user types their correct password and is refused. Nothing in the code looks wrong, there is no error message, and nothing appears in the logs.
> >
> > When a comparison behaves differently for real input than for test data, == on objects is the first thing to check.

---

### What you now know

- A String variable holds a reference to an object, not the letters.
- == asks whether two references point at the same object.
- .equals() asks whether two objects hold the same contents.
- For comparing text, you always want .equals().
- == can return true for text written in your code, which is why the bug survives testing and reaches production.

