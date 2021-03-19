# BackendFXServer
Market price handle exercise.

Backend receives messages and translate them into a PriceRepository adding a commision. An API serves those prices to clients. API-REST is not implemented yet.

A queue has been implemented in order to manage the messages. A thread will take the messages from it and storing them into a memory structure (PriceRepository).
Price is parsed into a structure where bid,ask,timestamp are stored. Bid and Ask are stored as int, this way there is not problem with decimals.
Timestamp is stored as String because there is not manipulation. It's just served to the client.

There is a test in drosa.test.
