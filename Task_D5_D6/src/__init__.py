from fastapi import FastAPI
from contextlib import asynccontextmanager
from src.db.main import init_db
from src.demo.routes import demo_router


@asynccontextmanager
async def lifespan(app: FastAPI):
    print("server is starting")
    await init_db()
    yield 
    print("server is shuting down")

app = FastAPI(
    title="My service",
    description="The best service ever",
    lifespan=lifespan
)


app.include_router(demo_router, tags=['demo'])