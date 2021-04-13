# DC_Tower_Elevator

# Implemention
In this Project the Elevators of the DC-Tower are simulated.
Each Elevator gets represented by a Thread. All Elevators run parallel and
are constantly waiting for requests. This is accomplished using a BlockingQueue,
assuming the Problem as a Producer/Consumer Objective. The choice of a 
BlockingQueue was made, because the datatype had to be threadsafe and had to 
implement the needed methods. 

# Request handling
Each Elevator gets one or more requests assigned. It checks if more requests
are in the queue that go to the same direction and are at least in the temporary 
spot or above. This accomplishes, that every Elevator can carry multiple Passangers
and multiple Requests at a time.

