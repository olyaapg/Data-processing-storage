from sqlmodel.ext.asyncio.session import AsyncSession
from typing import List, Dict, Any
from sqlalchemy import text

class DemoService:
    def __init__(self, session:AsyncSession):
        self.session = session
        
    async def get_cities(self):        
        result = await self.session.execute(text('SELECT DISTINCT(city) FROM airports'))
        result_list = []
        for r in result:
            result_list.append(r[0])
        return result_list
    
    async def get_airports(self):
        result = await self.session.execute(text('SELECT DISTINCT(airport_name) FROM airports'))
        result_list = []
        for r in result:
            result_list.append(r[0])
        return result_list
    
    async def get_airports_within_city(self, city:str):
        query = text("SELECT airport_name FROM airports WHERE city = :city")
        result = await self.session.execute(query, {"city": city})
        result_list = []
        for r in result:
            result_list.append(r[0])
        return result_list
    
    async def get_inbound_shedule(self, airport:str) -> List[Dict[str, Any]]:
        query = text("""SELECT to_char(scheduled_arrival_local, 'Day'), 
                               to_char(scheduled_arrival_local, 'HH24:MI'), 
                               flight_no, 
                               departure_airport_name 
                        FROM flights_v 
                        WHERE arrival_airport_name = :airport
                        ORDER BY scheduled_arrival_local""")
        result = await self.session.execute(query, {"airport": airport})
        data = []
        for r in result:
            flight_data = {
                "Days of week": r[0].strip(),
                "Time of arrival": r[1].strip(),
                "Flight no": r[2].strip(),
                "Origin": r[3].strip()
            }
            data.append(flight_data)
        return data
    
    async def get_outbound_shedule(self, airport:str) -> List[Dict[str, Any]]:
        query = text("""SELECT to_char(scheduled_departure_local, 'Day'), 
                               to_char(scheduled_departure_local, 'HH24:MI'), 
                               flight_no, 
                               arrival_airport_name 
                        FROM flights_v 
                        WHERE departure_airport_name = :airport
                        ORDER BY scheduled_departure_local""")
        result = await self.session.execute(query, {"airport": airport})
        data = []
        for r in result:
            flight_data = {
                "Days of week": r[0].strip(),
                "Time of departure": r[1].strip(),
                "Flight no": r[2].strip(),
                "Destination": r[3].strip()
            }
            data.append(flight_data)
        return data