from fastapi import APIRouter, Depends
from sqlmodel.ext.asyncio.session import AsyncSession
from typing import List, Dict, Any
from src.db.main import get_session
from http import HTTPStatus
from datetime import datetime
from .service import DemoService

demo_router = APIRouter(
    prefix="/demo"
)

@demo_router.get("/")
async def ping(session: AsyncSession = Depends(get_session)):
    return {"Hello": "World"}

#1
@demo_router.get("/cities", response_model=List, status_code=HTTPStatus.OK)
async def get_cities(session: AsyncSession = Depends(get_session)):
    cities = await DemoService(session).get_cities()
    return cities

#2
@demo_router.get("/airports", response_model=List, status_code=HTTPStatus.OK)
async def get_airports(session: AsyncSession = Depends(get_session)):
    airports = await DemoService(session).get_airports()
    return airports

#3
@demo_router.get("/airports/{city}", response_model=List, status_code=HTTPStatus.OK)
async def get_airports_within_city(city:str, session: AsyncSession = Depends(get_session)):
    airports = await DemoService(session).get_airports_within_city(city)
    return airports

#4
@demo_router.get("/schedule/inbound/{airport}", response_model=List[Dict[str, Any]], status_code=HTTPStatus.OK)
async def get_inbound_shedule(airport:str, session: AsyncSession = Depends(get_session)):
    shedule = await DemoService(session).get_inbound_shedule(airport)
    return shedule

#5
@demo_router.get("/schedule/outbound/{airport}", response_model=List[Dict[str, Any]], status_code=HTTPStatus.OK)
async def get_outbound_shedule(airport:str, session: AsyncSession = Depends(get_session)):
    shedule = await DemoService(session).get_outbound_shedule(airport)
    return shedule

#6
@demo_router.get("/routes/{origin}/{destination}", status_code=HTTPStatus.OK)
async def get_routes(
    origin: str,
    destination: str,
    departure_date: datetime,
    booking_class: str,
    connections_limit: int
):
    pass

#7
@demo_router.post("/booking", status_code=HTTPStatus.CREATED)
async def create_booking(
    origin: str,
    destination: str,
    departure_date: datetime,
    fare_conditions: str,
    passenger_name: str
):
    pass

#8
@demo_router.post("/checkin/{flight_id}", status_code=HTTPStatus.OK)
async def online_checkin(
    flight_id: int,
    passenger_name: str,
    seat_number: str
):
    pass