# A Garbage Collection simulator

## Overview
This is a simulator to simulate garbage collection mechanism by using Java. I wrote this in order to learn GC. It contains `mark and sweep`, `copying`, `reference counting` GC strategies. Here is the background knowledge: 

## Note
I only consider stack which saves references(pointer) and heap which saves objects. `Field` is also a kind of reference(pointer) which describes objects' attributes. I saved it into `fieldArea` which does not exist in real computer. Just for convenience. Memory size is 128. Here is the structure I want to describe. 

![structure](/img/1.png)

I only paid attention to GC strategies, no more other optimizations.