-- Clear existing data (optional)
DELETE FROM ESTABELECIMENTO_PROFISSIONAL;
DELETE FROM ESPECIALIDADE_PROFISSIONAL;
DELETE FROM PROFISSIONAL;
DELETE FROM ESPECIALIDADE;
DELETE FROM ESTABELECIMENTO;
DELETE FROM CLIENTE;

-- Insert specialties
INSERT INTO ESPECIALIDADE (id, nome) VALUES
    ('550e8400-e29b-41d4-a716-446655440001', 'Corte de Cabelo'),
    ('550e8400-e29b-41d4-a716-446655440002', 'Barba'),
    ('550e8400-e29b-41d4-a716-446655440003', 'Manicure'),
    ('550e8400-e29b-41d4-a716-446655440004', 'Pedicure'),
    ('550e8400-e29b-41d4-a716-446655440005', 'Massagem');

-- Insert professionals
INSERT INTO PROFISSIONAL (id, nome, horario_inicio, horario_fim, tarifa) VALUES
    ('660e8400-e29b-41d4-a716-446655440001', 'João Silva', '09:00:00', '18:00:00', 50),
    ('660e8400-e29b-41d4-a716-446655440002', 'Maria Souza', '10:00:00', '19:00:00', 60),
    ('660e8400-e29b-41d4-a716-446655440003', 'Carlos Oliveira', '08:00:00', '17:00:00', 70);

-- Link professionals to specialties
INSERT INTO ESPECIALIDADE_PROFISSIONAL (profissional_id, especialidade_id) VALUES
    ('660e8400-e29b-41d4-a716-446655440001', '550e8400-e29b-41d4-a716-446655440001'), -- João - Corte
    ('660e8400-e29b-41d4-a716-446655440001', '550e8400-e29b-41d4-a716-446655440002'), -- João - Barba
    ('660e8400-e29b-41d4-a716-446655440002', '550e8400-e29b-41d4-a716-446655440003'), -- Maria - Manicure
    ('660e8400-e29b-41d4-a716-446655440002', '550e8400-e29b-41d4-a716-446655440004'), -- Maria - Pedicure
    ('660e8400-e29b-41d4-a716-446655440003', '550e8400-e29b-41d4-a716-446655440005');  -- Carlos - Massagem

-- Insert establishments
INSERT INTO ESTABELECIMENTO (id, nome, endereco, horario_inicio, horario_fim) VALUES
    ('770e8400-e29b-41d4-a716-446655440001', 'Bela Beleza', 'Rua das Flores, 123', '08:00:00', '20:00:00'),
    ('770e8400-e29b-41d4-a716-446655440002', 'Barbearia Supimpa', 'Avenida Principal, 456', '09:00:00', '19:00:00');

-- Link professionals to establishments
INSERT INTO ESTABELECIMENTO_PROFISSIONAL (estabelecimento_id, profissional_id) VALUES
    ('770e8400-e29b-41d4-a716-446655440001', '660e8400-e29b-41d4-a716-446655440001'), -- Bela Beleza - João
    ('770e8400-e29b-41d4-a716-446655440001', '660e8400-e29b-41d4-a716-446655440002'), -- Bela Beleza - Maria
    ('770e8400-e29b-41d4-a716-446655440002', '660e8400-e29b-41d4-a716-446655440003'); -- Barbearia Supimpa - Carlos

-- Insert clients
INSERT INTO CLIENTE (id, nome, email, telefone) VALUES
    ('880e8400-e29b-41d4-a716-446655440001', 'Ana Paula', 'ana@email.com', '(11) 9999-8888'),
    ('880e8400-e29b-41d4-a716-446655440002', 'Roberto Alves', 'roberto@email.com', '(11) 7777-6666'),
    ('880e8400-e29b-41d4-a716-446655440003', 'Fernanda Lima', 'fernanda@email.com', '(11) 5555-4444');