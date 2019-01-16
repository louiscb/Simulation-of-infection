# Simulation of the spread of an infection in a population

## Problem

The task at hand is to model an infection spreading through a population, where
the model should adhere to these requirements:
- It the population is a NxN matrix. In this system every element in the matrix is either a sick or healthy individual.
- A sick individual can infect those in direct contact to them (with a user specified probability).
- Once an individual gets ill they can spread the disease.
- Probability that infected member die.
- After they get over the sickness they become immune.
- The simulation ends when no one in the population is ill.

The user specified parameters for this modeled simulation include:
- Size of population (N)
- Probability that an individual infects a non immune neighbour.
- How long an individual is going to be sick for, chosen randomly from a user specified interval [minDays, maxDays].
- The probability a sick member dies.
- The initial number of sick members and their locations in the matrix.

The output the simulation should give:
- How many members get infected per day (day specific).
- How many members die per day (day specific).
- How many members recover and become immune per day.
- The overall number of members that are ill per day.
- The overall number of members that got infected everyday (recovered or not).
- The overall number of deaths per day.

## Program

The way we modelled our solution was through a java program where we have a Population class, representing the qualities of the population that we need to track. This includes things such as the number of ill, dead, immune people. The modelled population is represented using a square matrix, whose size is dependent on the input of the user. It is the smallest square matrix that can be created using that input.
The elements of this matrix are the Members of the population which is our second Class. This class holds the information that is deemed important for the functionality of our program. This includes qualities such as their current state and position. It is used in the population class to populate objects such as the matrix representing the population and the list of each infected members’ neighbors so that they can be exposed to the possibility of infection. The state of these members is referenced to through another class (StatusType Class).

The final class is the main class which runs the simulation and provides the interaction with the user where it asks them to specify the inputs mentioned previously so that they are used to set the fields for the initiated population object.

The program starts by asking for inputs and based on those inputs it will run and everyday and it will print the values of the outputs mentioned in the Problem description. The randomisation in the running is using java's random function where the seeds are a list of predetermined large prime numbers as they provide the best results.  It will stop running once all the members are indicated to be dead, immune, or health. i.e no ill members are left in the population.

